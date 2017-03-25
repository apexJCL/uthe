var five = require("johnny-five");
var board = new five.Board();
var express = require('express');
var app = express();
var bodyParser = require('body-parser');

// VARS MEDITIONS
var pulses = 0;
var lastFlowRateTimer = 0;


// ENCODER

app.use(bodyParser.urlencoded( { extended: true }));
app.use(bodyParser.json());

var port = process.env.PORT || 8080;
var router = express.Router();

// ROUTES 

router.get('/', function(req, res){
	res.json({ message: 'Hooray!'})
});



app.use('/api', router);
app.listen(port);
console.log('Magic happens on port ' + port);


board.on("ready", function() {
	this.pinMode(2, five.Pin.INPUT);
	lastFlowPinState = 0;
  
  	var valvu = new five.Led(3);

  	// Check Digital Pin to see if theres a change
  	var x = this.digitalRead(2, function(value) {
    	// send the pin status to flowSignal helper
    	flowSignal(value);
    	//console.log("pinValue: " + value);
	});
  
	router.route('/flow').get(function(req, res){		
		var litres = 0;
		pulses = 0
		var time_flow = req.query.time;
		var litresAuth = req.query.maxflow;			
		valvu.on();		

		var interval = setInterval(function() {
			litres = pulses;
			litres /= 7.5;
			litres /= 60;
			
			if(litres >= litresAuth || time_flow == 0){
				valvu.off();
				console.log("Successful")
				res.json({ litres: litres, time : time_flow});
				clearInterval(interval)
			}else{
				console.log('Litres: ' + litres, ' time: ' + time_flow )
			}			
			time_flow --;
		}, 1000);

	});
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

function calculateDate (time) {
	var time = new Date ();
}