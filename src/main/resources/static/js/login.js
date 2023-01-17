var signupModal = document.getElementById('signupModalId');
window.onclick = function(event){
    if(event.target == signupModal){
        closeRegisterModal();
    }
}
function login(email, password){
    alert(email);
    alert(password);
    $http({
        method: 'POST', 
        url: '/authentication/authenticate',
        data: JSON.stringify({ "email": email, "password": password }),
      });
}
function register(){

}
function showRegisterModal(){
    document.getElementById('signupModalId').style.display='block';
}
function closeRegisterModal(){
    document.getElementById('signupModalId').style.display='none'
}
function saveAuthData(token){
    sessionStorage.setItem('token', token);
}
function clearAuthData(){
    sessionStorage.removeItem("token");
}
function getAuthData(){
    return sessionStorage.getItem("token");
}