const rl = require('readline').createInterface({
  input: process.stdin,
  output: process.stdout
}).on('line', function (line) {
  let input = line.split(" ");
  let array = Array.from({ length: Number(input[0]) }, () => false);
  array = solution(array, Number(input[1]));
  console.log(array.join("\n"));

  function solution(arr, length) {
    var result = [];
    if (length === 1) {
      for (var i = 0; i < arr.length; i++) {
        if (!arr[i]) result.push(String(i + 1));
      }
    } else {
      var arrCopy = arr.slice();
      for (var i = 0; i < arrCopy.length; i++) {
        if (!arr[i]) {
          arrCopy[i] = true;
          var _solution = solution(arrCopy, length - 1);
          for (var j = 0; j < _solution.length; j++) {
            result.push(String(i + 1) + " " + _solution[j]);
          }
        }
      }
    }
    return result;
  }
  process.exit();
});
