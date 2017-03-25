var five = require("johnny-five");
var board = new five.Board();
var Stream = require('stream');
var flow_stream = new Stream();

var layout = { fileopt : "overwrite", filename : "Water Flow" };

var pulses = 0;
var lastFlowRateTimer = 0;

board.on("ready", function() {
  this.pinMode(2, five.Pin.INPUT);
  lastFlowPinState = 0;

  // Check Digital Pin to see if theres a change
  var x = this.digitalRead(2, function(value) {
    // send the pin status to flowSignal helper
    flowSignal(value);
    //console.log("pinValue: " + value);
  });

  // Set how often to Emit data to Plotly
  setInterval(function() {
    var litres = pulses;
    litres /= 7.5;
    litres /= 60;
    console.log("Freq: " + pulses)
    console.log("Litros: " + litres)
    var time = getDateString();        
  }, 1000);
});

// helper function to keep track of pulses
function flowSignal (value) {
    if (value === 0) {
      lastFlowRateTimer ++;
      return;
    }
    if (value === 1) {
      pulses ++;
    }
    lastFlowPinState = value;
    flowrate = 1000.0;
    flowrate /= lastFlowRateTimer;
    lastFlowRateTimer = 0;
}

// little helper function to get a nicely formatted date string
function getDateString () {
  var time = new Date();
  // 14400000 is (GMT-4 Montreal)
  // for your timezone just multiply +/-GMT by 3600000
  var datestr = new Date(time - 14400000).toISOString().replace(/T/, ' ').replace(/Z/, '');
  return datestr;
}
