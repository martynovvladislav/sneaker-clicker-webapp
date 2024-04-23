import React, { useEffect } from 'react'
import './lootboxes.css'
import lootboxImage from '../assets/lootbox.png'
import coin from '../assets/coin.png'

async function fetchOpenBox(userId) {
    console.log("opening box...");
    return await fetch(
        "http://localhost:8081/users/" + userId + "/openbox",
        {
          method: "POST",
        }
    ).then((response) => response.json());
}

function lootboxes({userId, setPage, count, setCount, boxesAmount, setBoxesAmount, updateUser}) {
    const nonActiveBtnStyle = {
        'pointer-events': 'none'
    }

    const activeBtnStyle = {
        'pointer-events': 'all'
    }

    async function buyBox() {
        if (count >= 100) {
            await updateUser(userId, count - 100, boxesAmount + 1);
            setCount(count - 100);
            setBoxesAmount(boxesAmount + 1);
        }
    }

    async function openContainer() {
        if (boxesAmount > 0) {
            await updateUser(userId, count, boxesAmount - 1);
            setBoxesAmount(boxesAmount - 1);
            const sneaker = await fetchOpenBox(userId);
            console.log(sneaker);

            let result = document.createElement("div");
            result.id = "result";

            let img = document.createElement("img");
            img.setAttribute("src", "./src" + sneaker.imagePath.slice(1));

            let name = document.createElement("h3");
            name.innerText = sneaker.name;
            result.appendChild(img);
            result.appendChild(name);

            let dropChance = document.createElement("h4");
            dropChance.innerText = "Chance " + sneaker.dropChance * 100 + "%";

            if (sneaker.isNew) {
                let isNew = document.createElement("h3");
                isNew.innerText = "NEW SNEAKER!";
                result.appendChild(isNew);
            }

            result.appendChild(img);
            result.appendChild(name);
            result.appendChild(dropChance);
            document.getElementById("root").appendChild(result);
            let timeout = setTimeout(() => document.getElementById("result").remove(), 5000);
            result.addEventListener("click", () => {
                document.getElementById("result").remove();
                clearTimeout(timeout);
            })
            
        }
    }

    

    return(
    <>
        <div id="header">
            <div id="backButton" onClick={() => {
                if (document.getElementById("result") != undefined) {
                    document.getElementById("result").remove();
                }
                setPage("main");
            }}>←Back</div>
            <div id="coin-amount-lootboxes">
                <h3 id="coin-amount-value">{count}</h3>
                <img src={coin} id="coin-amount-image"/>
            </div>
        </div>
        
        <div id="lootbox">
            <img src={lootboxImage} 
            onClick={() => openContainer()}
            style = {boxesAmount > 0 ? activeBtnStyle : nonActiveBtnStyle}/>
            <div>{boxesAmount> 0 ? "Click to open! You have: " + boxesAmount : "No boxes right now! Buy one"}</div>
        </div>

        <div id="store">
            <img src={lootboxImage}/>
            <h5>100 coins</h5>
            <button id="buy-button" 
                style = {count >= 100 ? activeBtnStyle : nonActiveBtnStyle}
                onClick={() => buyBox()}>Buy</button>
        </div>
    </>
    )
}

export default lootboxes