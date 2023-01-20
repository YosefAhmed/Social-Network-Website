var token ="";
window.onload = function (){
    currentUser = JSON.parse(localStorage.getItem("currentUser"));
    requestOptions = {
        method: 'GET',
        headers: { 'Content-Type': 'application/json' },
    };
    getToken()
    .then((res) => {
        if (res.status == 200) {
            res.text().then(function(value){
                // alert(value)
                token = value;
                getPosts(value);
            });
        } else {
            throw Error("ERROR: "+res.statusText)
        }
    })
    .catch(console.error)

}
async function getToken(){
    return await fetch("/authenticated/getToken", window.requestOptions)

}
function getPostElement(name, content, date, numberOfLikes, numberOfComments){
    return `
    <div class="post-modal">
        <div class="container">
            <div class="postAccount-panel">
                <img src="images/manavatar.png" style="width: 10%; border-radius: 50%;">
                <div class="postAccount-panel-data">
                    <strong id="nameText">${name}</strong>
                    <small id="postDateText">${date}</small>
                </div>
            </div>
            <div class="postContent-panel">
                <p>${content}</p>
            </div>
            <div>
            <div class="postInteractions-panel">
                <button id="commentButton" type="submit" style="margin-right: 1%; width: 20%;" >comment</button>
                <textarea id="commentContent" class="post-textArea" name="commentTxt" placeholder="Write a comment.."></textarea>
                <button id="likeButton" type="submit" style="margin-left: 2%; width: 25%;" onclick="like(this, this.parentNode.parentNode)">Like</button>
            </div>
            <small id="numOfComments" style="color: gray;">${numberOfComments}</small>
            <small style="color: gray;"> comments</small>
            <small id="numOfLikes" style="color: gray;">${numberOfLikes}</small>
            <small style="color: gray;"> likes</small>
        </div>
        </div>
    </div>
    `;
    
}
function post(postContent) {
    $.ajax({
        url: "/addPost",
        type: "POST",
        data: JSON.stringify({
            "postContent":postContent,
            "publisher": currentUser
        }),
        headers: { "Content-Type": "application/json","Authorization": "Bearer "+ token},
        success: function (result) {
            console.log(result);
        },
        error: function (result) {
            console.log("error " + result.statusText);
        },
    });
    window.location.reload();
    // createPostElement(currentUser.name, postContent, date, numberOfLikes, numberOfComments)
}
function createPostElement(name, content, date, numberOfLikes, numberOfComments){
    let postElement = getPostElement(name, content, date, numberOfLikes, numberOfComments);
    let element = document.createElement("div");
    element.innerHTML = postElement;
    document.getElementById("wallModal").appendChild(element);
}

function getPosts(token){
    let posts = new Array();
    requestOptions = {
        method: 'GET',
        headers: { 'Content-Type': 'application/json', "Authorization": "Bearer "+ token},
    };
    fetch("/getPosts", requestOptions)
    .then((res) => {
        if (res.status == 200) {
            res.json().then(function(value){
                posts = value;
                posts.forEach((postObj)=>{
                    createPostElement(
                        postObj.publisher.name, 
                        postObj.content, 
                        postObj.date,
                        postObj.numberOfLikes,
                        postObj.numberOfComments)
                    console.log(postObj);
                });
            });
        } else {
            throw Error(res.statusText)
        }
    })
    .catch(console.error)
}
function like(element, parent) {
    let numOfLikes = Number(parent.querySelector("#numOfLikes").innerHTML);
    if (element.innerHTML == "Like") {
        element.innerHTML = "Liked";
        element.style.backgroundColor = "white";
        element.style.border = "2px solid #227bcf";
        element.style.color = "#227bcf";

        numOfLikes++;
    } else {
        element.innerHTML = "Like";
        element.style.backgroundColor = "#227bcf";
        element.style.border = "border: 0px";
        element.style.color = "white";
        numOfLikes--;
    }
    parent.querySelector("#numOfLikes").innerHTML = numOfLikes;
}
