'use strict';

class GameView {
	// 変数の初期化
	static w = 0;			// キャンバス横幅
	static h = 0;			// キャンバス高さ
	static element = null;	// 対象要素
	static rect = {};		// 対象矩形
	static funcTap = null;	// タップ実行関数

	// ビューの初期化
	static init(selectorId, {w, h}) {
		this.w = w;			// キャンバス横幅
		this.h = h;			// キャンバス高さ
		this.element = document.querySelector(selectorId);	// 要素を選択

		this.initTap();		// タップの初期化
		this.autoResize();	// 画面サイズの自動変更
	};

	//------------------------------------------------------------
	// 画面サイズの自動変更
	static autoResize() {
		// リサイズ時に実行する関数
		const funcResize = () => {
			this.calcRect();	// 対象矩形の計算
			this.fitCanvas();	// 全キャンバスを画面にフィット
		};
		funcResize();	// 初回実行

		// 遅延付き実行（短時間の連続実行を避ける）
		let timerId = null;		// 遅延実行用のタイマーID
		window.addEventListener('resize', function(e) {
			if (timerId !== null) {
				clearTimeout(timerId)	// 遅延中なら処理を削除
			}
			timerId = setTimeout(funcResize, 50);	// 遅延実行
		});
	};

	//------------------------------------------------------------
	// 対象矩形の計算
	static calcRect() {
		// ウィンドウ サイズ
		const winW = window.innerWidth;
		const winH = window.innerHeight;

		// アプリ矩形の計算
		this.rect.w = Math.min(winW, winH * this.w / this.h) | 0;
		this.rect.h = Math.min(winH, winW * this.h / this.w) | 0;
		this.rect.x = (winW - this.rect.w) / 2 | 0;
		this.rect.y = (winH - this.rect.h) / 2 | 0;
	};

	//------------------------------------------------------------
	// 全キャンバスを画面にフィット
	static fitCanvas() {
		this.element.querySelectorAll('canvas').forEach(o => {
			o.style.left   = `${this.rect.x}px`;
			o.style.top	   = `${this.rect.y}px`;
			o.style.width  = `${this.rect.w}px`;
			o.style.height = `${this.rect.h}px`;
		});
	};

	//------------------------------------------------------------
	// タップの初期化
	static initTap() {
		// 使用イベント
		const events = {
			// マウス系
			mousedown:   'down',	// 押下
			mouseup:     'up',		// 解放
			mousemove:   'move',	// 移動
			mouseenter:  'enter',	// 侵入
			mouseleave:  'up',  	// 離脱
			// タッチ系
			touchstart:  'down',	// 押下
			touchend:    'up',		// 解放
			touchmove:   'move',	// 移動
			touchcancel: 'up'   	// 離脱
		};

		// イベント処理関数
		const fnc = type => {
			return e => {
				// クライアント位置取得（モバイルではタッチを利用）
				const eX = (e.changedTouches ? e.changedTouches[0] : e).clientX;
				const eY = (e.changedTouches ? e.changedTouches[0] : e).clientY;

				// イベント位置の計算（相対サイズからゲーム内位置を求める）
				const x = ((eX - this.rect.x) * this.w / this.rect.w) | 0;
				const y = ((eY - this.rect.y) * this.h / this.rect.h) | 0;

				// 画面範囲外なら飛ばす
				if (x < 0 || this.w <= x
				 || y < 0 || this.h <= y) {
					return;
				}

				// 実行（XY位置とイベント種類を渡す）
				if (typeof this.funcTap === 'function') {
					this.funcTap(x, y, type);
				}
			}
		};

		// イベントの登録
		Object.keys(events).forEach(x => {
			const type = events[x];
			this.element.addEventListener(x, fnc(type));
		});
	};

	// タップ実行関数の追加
	static add(func) {
		this.funcTap = func;
	}
}
