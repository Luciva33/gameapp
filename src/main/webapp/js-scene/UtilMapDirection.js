'use strict';

class UtilMapDirection {
	// 方向の判定
	static check(x, y, w, h) {
		// 変数の初期化
		const rate = w / h;	// 比率
		let direction = '';	// 方向

		// 方向の判定　画面を×で分割して、上下左右のどこかを求める
		if (x / y > rate) {
			// 上か右
			if ((w - x) / y > rate) {
				direction = 'U';	// 上（Up）
			} else {
				direction = 'R';	// 右（Right）
			}
		} else {
			// 左か下
			if ((w - x) / y > rate) {
				direction = 'L';	// 左（Left）
			} else {
				direction = 'D';	// 下（Down）
			}
		}

		// 方向を戻す
		return direction;
	}
}

