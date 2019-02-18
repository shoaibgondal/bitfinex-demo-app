'use strict'

angular.module('bitfinex_ticker', ['ngWebSocket'])
    .factory('BitFinex', function ($websocket) {
        // Open a WebSocket connection
        var ws = $websocket("wss://api.bitfinex.com/ws/");
        var tickerData = [];
        ws.onMessage(function (event) {
            console.log('message: ', event.data);
            // parse message
            if (event.data.startsWith("[")) {
                var message = event.data.substring(1, event.data.length - 1);
                var values = message.split(",");

                if (values.length > 2) {
                    var ticker = {
                        "bid": values[1],
                        "bidSize": values[2],
                        "ask": values[3],
                        "askSize": values[4],
                        "dailyChange": values[5],
                        "dailyChangePerc": values[6],
                        "lastPrice": values[7],
                        "volume": values[8],
                        "high": values[9],
                        "low": values[10]
                    };

                    tickerData.push(ticker);
                    if (tickerData.length == 6) {
                        tickerData.shift();
                    }
                }
            }
        });
        ws.onError(function (event) {
            console.log('connection Error', event);
        });
        ws.onClose(function (event) {
            console.log('connection closed', event);
        });
        ws.onOpen(function () {
            console.log('connection open');
            ws.send('{"event":"subscribe","channel":"ticker", "pair" : "BTCUSD"}');
        });
        return {
            tickerData: tickerData,
            status: function () {
                return ws.readyState;
            }
        };
    })
    .controller('bitFinexController', function ($scope, BitFinex) {
        $scope.BitFinex = BitFinex;

        $scope.userPrice = 0;

        $scope.priceHigh = false;
        $scope.priceLow = false;

        $scope.comparePrice = function() {
            var ticker = $scope.BitFinex.tickerData[$scope.BitFinex.tickerData.length - 1];
            if (parseFloat(ticker.bid) > parseFloat($scope.userPrice)) {
                $scope.priceHigh = true;
                $scope.priceLow = false;
            } else {
                $scope.priceLow = true;
                $scope.priceHigh = false;

            }
            console.log("Bid " + ticker.bid + ", User " + $scope.userPrice);
        };
    });