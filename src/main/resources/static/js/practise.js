'use strict';
var user = {
    name:"qinjiang",
    age:23,
    sex:'男'
};
// var jsonUser = JSON.stringify(user);
// console.log(jsonUser);
var obj = JSON.parse('{"name":"qinjiang","age":23,"sex":"男"}');
console.log(obj);