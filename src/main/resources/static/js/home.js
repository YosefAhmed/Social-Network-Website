function post(postContent){
    var postElement = `
        <div class="post-modal">
            <div class="container">
                <div class="postAccount-panel">
                    <img src="../static/images/manavatar.png" style="width: 10%; border-radius: 50%;">
                    <div class="postAccount-panel-data">
                        <strong id="nameText">Yousef Ahmed</strong>
                        <small id="postDateText">10/01/2023</small>
                    </div>
                </div>
                <div class="postContent-panel">
                    <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
                </div>
                <div>
                <div class="postInteractions-panel">
                    <button id="commentButton" type="submit" style="margin-right: 1%; width: 20%;" >comment</button>
                    <textarea id="commentContent" class="post-textArea" name="commentTxt" placeholder="Write a comment.."></textarea>
                    <button id="likeButton" type="submit" style="margin-left: 2%; width: 25%;" onclick="like(this, this.parentNode.parentNode)">Like</button>
                </div>
                <small id="numOfComments" style="color: gray;">0</small>
                <small style="color: gray;"> comments</small>
                <small id="numOfLikes" style="color: gray;">0</small>
                <small style="color: gray;"> likes</small>
            </div>
            </div>
        </div>
    `;
    var element = document.createElement('div');
    element.innerHTML = postElement;
    document.getElementById('wallModal').appendChild(element);
}
function like(element, parent){
    let numOfLikes = Number(parent.querySelector('#numOfLikes').innerHTML);
    if(element.innerHTML == "Like"){
        element.innerHTML = "Liked";
        element.style.backgroundColor = "white";
        element.style.border = "2px solid #227bcf";
        element.style.color = "#227bcf";
        
        numOfLikes++;
    }
    else{
        element.innerHTML = "Like";
        element.style.backgroundColor = "#227bcf";
        element.style.border = "border: 0px";
        element.style.color = "white";
        numOfLikes--;
    }
    parent.querySelector('#numOfLikes').innerHTML = numOfLikes;
}