import { useEffect, useState } from 'react'
import coin from '../assets/coin.png'
import './mainPage.css'

function mainPage({setPage, count, setCount, updateUser, userId, boxesAmount}) {
    const [scale, setScale] = useState("scale(1)");
    const styles = {
      'transform': scale
    }
    const[lastTimeUpdated, setLastTimeUpdated] = useState(new Date().getTime());

    useEffect(() => {
      let millis = new Date().getTime();
      if (millis - lastTimeUpdated > 3000) {
        updateUser(userId, count, boxesAmount);
        setLastTimeUpdated(millis);
      }
    }, [count])

    return (
      <>
        <div id="coin-amount">
          <h3 id="coin-amount-value">{count}</h3>
          <img src={coin} id="coin-amount-image"/>
        </div>
  
        <div>
        <img src={coin} id="coin-image" onClick={() => setCount((count) => count + 10)} 
          style={styles} onMouseDown={() => setScale("scale(0.9)")} onMouseUp={() => setScale("scale(1)")}/>
        </div>
        <h1>Click!</h1>
  
        <div id="main-menu">
          <button id="main-menu-button-loot" onClick={() => {
            updateUser(userId, count, boxesAmount);
            setPage("lootboxes");
          }}><h4>LOOTBOXES</h4></button>
          <div className='main-menu-splitter'></div>
          <button id="main-menu-button-collection" onClick={() => {
            updateUser(userId, count, boxesAmount);
            setPage("collection");
          }}><h4>COLLECTION</h4></button>
        </div>
      </>
    )
  }
  
export default mainPage