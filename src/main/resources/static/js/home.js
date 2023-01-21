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
function getPostElement(postId, name, date, content, liked,numberOfLikes, numberOfComments){
    return ` 
    <div class="post-modal" id="${postId}">
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
                <button id="likeButton" type="submit" style= "margin-left: 2%; width: 25%; background-color: white; border: 2px solid rgb(34, 123, 207); color: rgb(34, 123, 207);"
                onclick="like(this, this.parentNode.parentNode)">${liked}</button>
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
function createPostElement(post){
    let postElement = getPostElement(post.id,post.name, post.date, post.content, post.liked,post.numOfLikes, post.numOfComments);
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
                    let listOfLikes = postObj.listOfLikes;
                    let listOfComments = postObj.listOfComments;
                    let liked = "Like";
                    console.log(postObj.postDate);
                    let date = new Date(postObj.postDate.substring(0,10));
                    if(listOfLikes.some((user)=>currentUser.id === user.id)){
                        liked = "Dislike"
                    }
                    console.log();
                    createPostElement(
                        {
                            "id":postObj.id,
                            "name":postObj.publisher.name, 
                            "date": date.toLocaleDateString()+" "+date.toLocaleTimeString(),
                            "content":postObj.content,
                            "liked":liked, 
                            "numOfLikes":postObj.numberOfLikes,
                            "numOfComments":postObj.numberOfComments})
                            // console.log(postObj);
                        });
                });
        } else {
            throw Error(res.statusText)
        }
    })
    .catch(console.error)
}
function like(element, parent) {
    let postId = parent.parentNode.parentNode.id;
    let numOfLikes = Number(parent.querySelector("#numOfLikes").innerHTML);
    let interaction = "";
    if (element.innerHTML == "Like") {
        interaction = "LIKE";
        element.innerHTML = "Dislike";
        numOfLikes++;
    } else {
        interaction = "DISLIKE";
        element.innerHTML = "Like";
        numOfLikes--;
    }
    $.ajax({
        url: "/interactWithPost",
        type: "POST",
        data: JSON.stringify({
            "postId":postId,
            "userId": currentUser.id,
            "numberOfLikes": numOfLikes,
            "interaction": interaction
        }),
        headers: { "Content-Type": "application/json","Authorization": "Bearer "+ token},
        success: function (result) {
            console.log(result);
        },
        error: function (result) {
            if (element.innerHTML == "Like") {
                interaction = "LIKE";
                element.innerHTML = "Liked";
                numOfLikes++;
            } else {
                interaction = "DISLIKE";
                element.innerHTML = "Like";
                numOfLikes--;
            }
        },
    });
    parent.querySelector("#numOfLikes").innerHTML = numOfLikes;
}
