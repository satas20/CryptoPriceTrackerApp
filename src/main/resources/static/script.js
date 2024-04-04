// Countdown timer
var date = new Date('April 19, 2024 07:35:00');

var countdownElement = document.getElementById('countdown');
var solanaPrice = document.getElementById('solana-price');
var bitcoinPrice = document.getElementById('bitcoin-price');
var ethereumPrice = document.getElementById('ethereum-price');

function updateCountdown() {
    var now = new Date();

    var timeDiff = date - now;
    var days = Math.floor(timeDiff / (1000 * 60 * 60 * 24));
    var hours = Math.floor((timeDiff % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    var minutes = Math.floor((timeDiff % (1000 * 60 * 60)) / (1000 * 60));
    var seconds = Math.floor((timeDiff % (1000 * 60)) / 1000);

    // Pad single digit values with leading zero
    days = days < 10 ? "0" + days : days;
    hours = hours < 10 ? "0" + hours : hours;
    minutes = minutes < 10 ? "0" + minutes : minutes;
    seconds = seconds < 10 ? "0" + seconds : seconds;

    document.querySelector('.days').textContent = days;
    document.querySelector('.hours').textContent = hours;
    document.querySelector('.minutes').textContent = minutes;
    document.querySelector('.seconds').textContent = seconds;
}

function fetchPrices() {
    // Proxy URL to fetch cryptocurrency prices through CORS Anywhere

    // Fetch Solana price

    fetch('http://localhost:8080/currPriceSOL')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // Parse the response body as JSON
        })
        .then(data => {
            // Handle the parsed JSON data
            const formattedPrice = parseFloat(data).toFixed(2);

            solanaPrice.textContent = formattedPrice +"💲";
            console.log(data);
        })
        .catch(error => {
            // Handle errors during the fetch operation
            console.error('Error fetching data:', error);
        });
    fetch('http://localhost:8080/currPriceBTC')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // Parse the response body as JSON
        })
        .then(data => {
            // Handle the parsed JSON data
            const formattedPrice = parseFloat(data).toFixed(2);
            bitcoinPrice.textContent = formattedPrice +"💲";
            console.log(data);
        })
        .catch(error => {
            // Handle errors during the fetch operation
            console.error('Error fetching data:', error);
        });
    fetch('http://localhost:8080/currPriceETH')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // Parse the response body as JSON
        })
        .then(data => {
            // Handle the parsed JSON data
            const formattedPrice = parseFloat(data).toFixed(2);

            ethereumPrice.textContent = formattedPrice +"💲";
            console.log(data);
        })
        .catch(error => {
            // Handle errors during the fetch operation
            console.error('Error fetching data:', error);
        });


}

// Initial call to update countdown
updateCountdown();

// Update countdown every second
setInterval(updateCountdown, 1000);

// Initial call to fetch prices
fetchPrices();

// Update prices every 2 seconds
setInterval(fetchPrices, 2000);







