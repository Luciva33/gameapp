//スコア
let score;



let isGameOver = false;

//落下サイクル(指定ミリ秒ごとに１つ下に落ちる)
const fallSpeed = 200;

//タイマーID    Nanは数値版のNULL
let timerId = NaN;


//ブロック1マスの大きさ
const blockSize = 30;
//ボードサイズ
const boardRow = 20;
const boardCol = 10;
//キャンバスの取得
const cvs = document.getElementById("cvs");
//2dコンテキストを取得
const ctx = cvs.getContext("2d");
//キャンバスサイズ
const canvasW = blockSize * boardCol;
const canvasH = blockSize * boardRow;
cvs.width = canvasW;
cvs.height = canvasH;
//コンテナの設定
const container = document.getElementById("container");
container.style.width = canvasW + 'px';



// テトリミノの設定
const tetSize = 4;

const tetTypes = [ // ７種類のミノの形を用意
    {},
    {
        type:
            [
                [0, 0, 0, 0],
                [0, 1, 1, 0],
                [0, 1, 1, 0],
                [0, 0, 0, 0],
            ],
        color: "purple"
    },
    {
        type:
            [
                [0, 0, 0, 0],
                [0, 1, 0, 0],
                [1, 1, 1, 0],
                [0, 0, 0, 0],
            ],
        color: "red"
    },
    {
        type:
            [
                [0, 0, 0, 0],
                [1, 1, 0, 0],
                [0, 1, 1, 0],
                [0, 0, 0, 0],
            ],
        color: "blue"
    },
    {
        type:
            [
                [0, 0, 0, 0],
                [0, 0, 1, 1],
                [0, 1, 1, 0],
                [0, 0, 0, 0],
            ],
        color: "green"
    },
    {
        type:
            [
                [0, 0, 0, 0],
                [1, 1, 1, 1],
                [0, 0, 0, 0],
                [0, 0, 0, 0],
            ],
        color: "skyblue"
    },
    {
        type:
            [
                [0, 0, 0, 0],
                [1, 1, 1, 0],
                [0, 0, 1, 0],
                [0, 0, 0, 0],
            ],
        color: "coral"
    },
    {
        type:
            [
                [0, 0, 0, 0],
                [0, 0, 1, 0],
                [1, 1, 1, 0],
                [0, 0, 0, 0],
            ],
        color: "orange"
    },
];
// 選択されたテトリミノ
let tet;
// 選択されたテトリミノのインデックス
let tet_idx;

//テトリスミノのオフセット量（何マス分ずれているか）
let offsetX = 0;
let offsetY = 0;

//ボード本体
const board = [];

//描画処理
function draw() {
    //塗りに黒を設定
    ctx.fillStyle = '#000';
    //キャンバスを塗りつぶす
    ctx.fillRect(0, 0, canvasW, canvasH);

    //ボードに存在しているブロックを塗る
    for (let y = 0; y < boardRow; y++) {
        for (let x = 0; x < boardCol; x++) {
            if (board[y][x]) {
                drawBlock(x, y, board[y][x]);

            }
        }
    }



    //テトリスミノの描画
    for (let y = 0; y < tetSize; y++) {
        for (let x = 0; x < tetSize; x++) {
            //javasciptのif文は,もし1だったら  true false の結果だけでなく0かそれ以外で判定できる
            //条件式を書いてもいい != 0
            if (tet[y][x]) {
                //四角を描画
                drawBlock(offsetX + x, offsetY + y);

            }
        }
    }

    //スコア画面
    drawScore();

    //ゲームオーバー時に[GAME OVER ]の文字を表示
    if (isGameOver) {
        const s = "GAME OVER";
        ctx.font = "40px 'Ms ゴシック'";
        const w = ctx.measureText(s).width;
        const x = canvasW / 2 - w / 2;
        const y = canvasH / 2 - 20;
        ctx.fillText(s, x, y);

        const p = "もう一度プレイする？"
        ctx.font = "15px 'Ms ゴシック'";
        const w2 = ctx.measureText(p).width;
        const x2 = canvasW / 2 - w2 / 4;
        const y2 = canvasH / 2 + 40;
        ctx.fillStyle = "white";
        ctx.fillText(p, x2, y2);
        
        //データベースにスコア数を送る
        //ランキング表示
    }





}
//ブロック一つを描画する　ポイントの場所ポイントの場所
function drawBlock(x, y, idx = tet_idx) {
    let px = x * blockSize;
    let py = y * blockSize;
    //塗りを設定
    ctx.fillStyle = tetTypes[idx].color;
    ctx.fillRect(px, py, blockSize, blockSize);
    //線を設定
    ctx.strokeStyle = 'black';
    //線を描画
    ctx.strokeRect(px, py, blockSize, blockSize);
};

//スコア画面
function drawScore() {
    const s = "SCORE:" + score; //描画する文字
    ctx.font = "29px 'MS ゴシック'"; //文字の大きさとフォント設定
    const w = ctx.measureText(s).width;
    const x = 9; //描画するx座標
    const y = 34; //描画するy座標
    ctx.fillStyle = "white"; //文字色
    ctx.fillText(s, x, y);
}

//指定された方向に移動できるか？（x移動量,移動量） dx,dy判定はどっちの方向に移動したいか判定 右なら→
function canMove(dx, dy, nowTet = tet) {
    for (let y = 0; y < tetSize; y++) {
        for (let x = 0; x < tetSize; x++) {
            if (nowTet[y][x]) {
                let nx = offsetX + x + dx;
                let ny = offsetY + y + dy;
                if (ny < 0 || nx < 0 || ny >= boardRow || nx >= boardCol || board[ny][nx]) {
                    return false;
                }
            }
        }
    }
    return true;
}

//回転処理
function createRotateTet() {
    //新規ミノ用の配列用意
    let newTet = [];
    for (let y = 0; y < tetSize; y++) {
        newTet[y] = [];
        for (let x = 0; x < tetSize; x++) {
            //時計回りに９０度回転させる
            newTet[y][x] = tet[tetSize - 1 - x][y];

        }
    }
    return newTet;
}

//反回転
// newTet[y][x] = tet[x][tetSize - 1 - y];
function createRotateTet2() {
    //新規ミノ用の配列用意
    let newTet2 = [];
    for (let y = 0; y < tetSize; y++) {
        newTet2[y] = [];
        for (let x = 0; x < tetSize; x++) {
            //時計回りに９０度回転させる
            newTet2[y][x] = tet[x][tetSize - 1 - y];
        }
    }
    return newTet2;
}





// 移動操作
document.addEventListener("keydown", (e) => {
    if (isGameOver) return;   //定型文　勝手に移動できないように
    switch (e.key) {
        case "ArrowLeft":
        case "a":
            if (canMove(-1, 0)) offsetX--;
            break
        case "ArrowRight":
        case "d":
            if (canMove(1, 0)) offsetX++;
            break
        case "ArrowUp":
        case "w":
            if (canMove(0, -1)) offsetY--;
            break
        case "ArrowDown":
        case "s":
            if (canMove(0, 1)) offsetY++;
            break
        case " ":// Spaceキーは半角空白
            let newTet = createRotateTet();
            if (canMove(0, 0, newTet)) tet = newTet;
            break;
        case "x":// Spaceキーは半角空白
            let newTet2 = createRotateTet2();
            if (canMove(0, 0, newTet2)) tet = newTet2;
            break;
    }
    draw();
});

//ゲームオーバー時にEnterを押すと最初から開始
document.addEventListener("keydown", (e) => {
    if (isGameOver) {
        switch (e.key) {
            case "Enter":
                init();
                break;
        }
    }

})

// 揃ったラインを消す
function clearLine() {
    // ボードの一番上の行から順番にチェック
    for (let y = 0; y < boardRow; y++) {
        // １列揃ってると仮定して、trueにしておく。
        let isLineOK = true;
        // 列に０が入ってるかチェック
        for (let x = 0; x < boardCol; x++) {
            if (board[y][x] == 0) {
                // 揃っていないのでfalseにしてbreakする
                isLineOK = false;
                break;
            }
        }

        // １列揃っていたら（falseになってなかったら）
        if (isLineOK) {
            // 揃っている行から上に向かってfor文を回す
            for (let ny = y; ny > 0; ny--) {
                for (let nx = 0; nx < boardCol; nx++) {
                    // 1列上の情報をコピーする
                    board[ny][nx] = board[ny - 1][nx];
                }
            }
            score += 1000;
        }
    }
}


//自動落下
function dropTet() {
    //下に移動できるなら
    if (canMove(0, 1)) {
        offsetY++;
    } else {
        //落下できなければ固定
        fixTet();
        //そろったラインがあったら消す
        clearLine();
        // 次のミノをランダムに用意
        tet_idx = randomIdx();
        tet = tetTypes[tet_idx].type;
        //次のテトリスミノのためにoffsetを初期位置に戻す
        initStartPos();

        //次のtetを出せなかったら、フラグをtrueにする
        if (!(canMove(0, 0))) {
            isGameOver = true;
            clearInterval(timerId);
        }
    }
    draw();
}


//自動落下処理
function initStartPos() {
    offsetX = boardCol / 2 - tetSize / 2;
    offsetY = 0;
}
//固定する
function fixTet() {
    for (let y = 0; y < tetSize; y++) {
        for (let x = 0; x < tetSize; x++) {
            if (tet[y][x]) {
                board[offsetY + y][offsetX + x] = tet_idx;
            }
        }
    }
}

// ランダムな整数を１つ決める（テトリミノが７種類なので、1～7で出す）
function randomIdx() {
    idx = Math.floor(Math.random() * (tetTypes.length - 1)) + 1;
    return idx;
}

//初期化処理
function init() {
    //スコアを0にする
    score = 0;
    //isGameOverフラグをfalseにする
    isGameOver = false;

    //ボードを(20*10を0埋め)
    for (let y = 0; y < boardRow; y++) {
        board[y] = [];
        for (let x = 0; x < boardCol; x++) {
            board[y][x] = 0;
        }
    }
    // 最初のミノをランダムに用意
    tet_idx = randomIdx();
    tet = tetTypes[tet_idx].type;


    initStartPos();
    timerId = setInterval(dropTet, fallSpeed);

    draw();
}