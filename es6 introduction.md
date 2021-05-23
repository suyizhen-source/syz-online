## es6介绍
- ECMAScript 6.0（以下简称 ES6）是 JavaScript 语言的下一代标准。  

- es6是一套标准，一套规范，JavaScript很好的遵循了这套规范。  

## 基本用法
##### let声明变量:
- var 声明的变量没有局部作用域
- let 声明的变量有局部作用域
```
{
var a = 0
let b = 1
}
console.log(a)  // 0
console.log(b)  // ReferenceError: b is not defined
```
- var 可以声明多次
- let 只能声明一次
```
var m = 1
var m = 2
let n = 3
let n = 4
console.log(m)  // 2
console.log(n)  // Identifier 'n' has already been declared
```
##### const声明常量:
- 声明之后不允许改变  
```   
const PI = "3.1415926"
PI = 3  // TypeError: Assignment to constant variable.
```
- 一但声明必须初始化，否则会报错

```
const MY_AGE  // SyntaxError: Missing initializer in const declaration
```
##### 解构赋值:
- 数组解构
```
// 传统
let a = 1, b = 2, c = 3
console.log(a, b, c)
// ES6
let [x, y, z] = [1, 2, 3]
console.log(x, y, z)
```
- 对象解构
```
let user = {name: 'Helen', age: 18}
// 传统
let name1 = user.name
let age1 = user.age
console.log(name1, age1)
// ES6
let { name, age } =  user//注意：结构的变量必须是user中的属性
console.log(name, age)
```
##### 模板字符串
- 多行字符串
```
let string1 =  `Hey,
can you stop angry now?`
console.log(string1)
// Hey,
// can you stop angry now?
```
- 字符串插入变量和表达式。
```
let name = "Mike"
let age = 27
let info = `My Name is ${name},I am ${age+1} years old next year.`
console.log(info)
// My Name is Mike,I am 28 years old next year.
```
- 字符串中调用函数
```
function f(){
    return "have fun!"
}
let string2 = `Game start,${f()}`
console.log(string2);  // Game start,have fun!
```
##### 声明对象简写
```
const name = "Amy"
const age = 12
// 传统
const person1 = {name: name,age: age}
console.log(person1)
// ES6
const person2 = {name,age}
console.log(person2) //{age: 12, name: "Amy"}
```
##### 定义方法简写
```
// 传统
const person1 = {
    sayHi:function(){
        console.log("Hi")
    }
}
person1.sayHi();//"Hi"
// ES6
const person2 = {
    sayHi(){
        console.log("Hi")
    }
}
person2.sayHi()  //"Hi"
```
##### 对象拓展运算符
- 浅拷贝对象
```
let person1 = {name: "Amy", age: 15}
let someone = { ...person1 }
console.log(someone)  //{name: "Amy", age: 15}
```
- 合并对象
```
let age = {age: 15}
let name = {name: "Amy"}
let person2 = {...age, ...name}
console.log(person2)  //{age: 15, name: "Amy"}
```
##### 箭头函数
- 参数 => 函数体
  - 当箭头函数没有参数或者有多个参数，要用 () 括起来。
  - 当箭头函数函数体有多行语句，用 {} 包裹起来，表示代码块。
  - 当只有一行语句，并且需要返回结果时，可以省略 {} , 结果会自动返回。 
```
// 传统
var f1 = function(a){
    return a
}
console.log(f1(1))
// ES6
var f2 = a => a
console.log(f2(1))
```
```
// 传统
var f3 = (a,b) => {
    let result = a+b
    return result
}
console.log(f3(6,2))  // 8
// es6
var f4 = (a,b) => a+b
```
