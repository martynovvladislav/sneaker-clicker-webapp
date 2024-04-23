import { useEffect, useState } from 'react'
import './App.css'
import MainPage from './mainPage/mainPage'
import Lootboxes from './lootboxes/lootboxes'
import Collection from './collection/collection'

async function addUser(userId) {
  await fetch(
    "http://localhost:8081/users/" + userId,
    {
      method: "POST"
    }
  )
  .then(() => console.log("new user created"))
  .catch((error) => console.log("user already exist"))
}

async function updateUser(userId, coinsAmount, boxesAmount) {
  await fetch(
    "http://localhost:8081/users",
    {
      method: "PUT",
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify({ 
        id: userId,
        coinsAmount: coinsAmount,
        boxesAmount: boxesAmount
      })
    }
  )
  .then(() => console.log("new data sended"))
  .catch((error) => console.log(error))
}

async function initialize(userId, setCount, setBoxesAmount) {
    await addUser(userId);
    setTimeout(() => console.log("setting up info: " + userId), 1000);
    console.log("getting init data...");
    await fetch(
      "http://localhost:8081/users" + "/" + userId,
      {
        method: "GET"
      }
    )
    .then((response) => response.json())
    .then((userDto) => {
      setCount(userDto['coinsAmount']);
      setBoxesAmount(userDto['boxesAmount']);
    })
    .catch((error) => console.log(error))
}

//const tg = window.Telegram.WebApp;

function App() {
  const [page, setPage] = useState("main")
  const [count, setCount] = useState(0);
  const [boxesAmount, setBoxesAmount] = useState(0);
  const[userId, setUserId] = useState(1); //tg.initDataUnsafe.user.id

  useEffect(() => {
    initialize(userId, setCount, setBoxesAmount);
    //tg.ready();
  }, []);

  // const onClose = () => {
  //   tg.close();
  // }

  if (page == "main") {
    return (<MainPage setPage={setPage} count={count} setCount={setCount} updateUser={updateUser} userId={userId} boxesAmount={boxesAmount}/>)
  } else if (page == "lootboxes") {
    // updateUser(userId, count, boxesAmount);
    return (<Lootboxes userId={userId} setPage={setPage} count={count} setCount={setCount} boxesAmount={boxesAmount} setBoxesAmount={setBoxesAmount} updateUser={updateUser}/>)
  } else {
    // updateUser(userId, count, boxesAmount);
    return (<Collection setPage={setPage} userId={userId}/>)
  }
}

export default App
