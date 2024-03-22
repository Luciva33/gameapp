const accounts = document.getElementById("accounts");
getAccounts();

async function getAccounts(){
	//AccoountsDataへリクエスト、レスポンスをJson形式で受け取る
	let response = await fetch("AccountsData");
	let root = await response.json();
	
	//受け取った情報を表示する
	for(const account of root.results){
		 account.innerHTML += `id:${account.USER_ID}
		  名前:${user.NAME} <br>`;
	}
	
	
}