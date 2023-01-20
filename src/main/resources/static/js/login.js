window.onload = function(){
  //TODO check if rememberMe is enabled

};


var signupModal = document.getElementById('signupModalId');
window.onclick = function(event){
    if(event.target == signupModal){
        closeRegisterModal();
    }
}

var token = "";
function login(email, password, rememberMe){
    console.log(rememberMe);
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ "email": email, "password": password, "rememberMe": rememberMe})
    };
    fetch("/authenticated/login", requestOptions)
        .then((res) => {
            if (res.status == 200) {
                res.text().then(function(value){
                    saveAuthData(value);
                });
                window.location.reload();
            } else {
                throw Error(res.statusText)
            }
        })
        .catch(console.error)

}
function register(){

}
function showRegisterModal(){
    document.getElementById('signupModalId').style.display='block';
}
function closeRegisterModal(){
    document.getElementById('signupModalId').style.display='none'
}
function saveAuthData(data){
    localStorage.setItem("currentUser", data);
}
function clearAuthData(){
    localStorage.removeItem("token");
}
function getAuthData(){
    return localStorage.getItem("token");
}