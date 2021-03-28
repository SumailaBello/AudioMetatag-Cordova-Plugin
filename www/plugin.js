
var exec = require('cordova/exec');

var PLUGIN_NAME = 'AudioMetatag';

var AudioMetatag = {
  echo: function(phrase, cb) {
    exec(cb, null, PLUGIN_NAME, 'echo', [phrase]);
  },
  getDate: function(cb) {
    exec(cb, null, PLUGIN_NAME, 'getDate', []);
  },
  getFiles: function(cb) {
    exec(cb, null, PLUGIN_NAME, 'getFiles', []);
  }
};

module.exports = MyCordovaPlugin;
