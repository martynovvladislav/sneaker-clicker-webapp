import React, { useEffect } from 'react'
import './collection.css'
import aj4 from '../assets/air-jordan.png'
import jh from '../assets/jordan-high.png'
import gs from '../assets/best-shoes.png'

async function fetchUserCollection(userId) {
    console.log("fetching user collection...");
    return await fetch(
        `http://localhost:8081/sneakers?user_id=${userId}`,
        {
          method: "GET",
        }
    ).then((response) => response.json());
}

function renderCollection(sneakerCollection) {
    let parent = document.getElementById("show-collection");
    for(let i = 0; i < sneakerCollection.length; i++) {
        let item = document.createElement("div");
        item.className = "collection-item";

        let img = document.createElement("img");
        img.setAttribute("src", "./src" + sneakerCollection[i].imagePath.slice(1));
        if (!sneakerCollection[i].isPresent) {
            img.style.filter = "grayscale(100%)";
        }
        item.appendChild(img);

        let title = document.createElement("h4");
        title.innerText = sneakerCollection[i].name;
        item.appendChild(title);

        parent.appendChild(item);
    }
}

function collection({setPage, userId}) {
    useEffect(() => {
        async function getCollection() {
            const sneakerCollection = await fetchUserCollection(userId);
            console.log(sneakerCollection);
            renderCollection(sneakerCollection);
        }
        getCollection();
    }, []);

    return (
    <>
        <div id="main-collection">
            <div id="backButton-collection" onClick={() => setPage("main")}>←Back</div>
            <h2>Your collection</h2>
            <div id="show-collection">
            </div>
        </div>
    </>
    )
}

export default collection