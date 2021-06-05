## vue介绍
- Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的渐进式框架。
- Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。

## 基本用法
#### 基本数据渲染和指令:
- v-bind
- {{}}
```
<div id="app">
    <h1 v-bind:title="message">
            {{content}}
    </h1>
</div>
<script src="vue.min.js"></script>
<script>
     new Vue({
        el: '#app',
        data: {
            content: '我是标题',
            message: '页面加载于 ' + new Date().toLocaleString()
        }
    })
 </script>
```
#### 双向数据绑定
- v-model 
```
<!-- v-bind:value只能进行单向的数据渲染 -->
<input type="text" v-bind:value="searchMap.keyWord">
<!-- v-model 可以进行双向的数据绑定  -->
<input type="text" v-model="searchMap.keyWord">
```
#### 事件
- v-on
```
<button v-on:click="search()">查询</button>
<!-- v-on 指令的简写形式 @ -->
<button @click="search()">查询</button>
```
#### 修饰符(Modifiers)
- 以半角句号（.）指明的特殊后缀，用于指出一个指令应该以特殊方式绑定。
- .prevent 修饰符告诉 v-on 指令对于触发的事件调用 event.preventDefault()：即阻止事件原本的默认行为
```
<form action="save" v-on:submit.prevent="onSubmit">
    <label for="username">
        <input type="text" id="username" v-model="user.username">
        <button type="submit">保存</button>
    </label>
</form>
```
#### 条件渲染
- v-if：条件指令
```
<input type="checkbox" v-model="ok">
<h1 v-if="ok">show</h1>
<h1 v-else>hide</h1>
```
#### 列表渲染
- v-for：列表循环指令
```
<ul>
    <!-- 如果想获取索引，则使用index关键字，注意，圆括号中的index必须放在后面 -->
    <li v-for="(n, index) in 5">{{ n }} - {{ index }} </li>
</ul>
```
```
<script>
    new Vue({
        el: '#app',
        data: {
             userList: [
             { id: 1, username: 'helen', age: 18 },
             { id: 2, username: 'peter', age: 28 },
             { id: 3, username: 'andy', age: 38 }
             ]
        }
     })
</script>
<div id="app">
    <table border="1">
        <tr v-for="user in userList">
             <td>{{user.username}}</td>
             <td>{{user.age}}</td>
         </tr>
     </table>
</div>
```
#### 组件
- 局部组件
```
 <div id="app">
        <Navbar></Navbar>
    </div>
    <script src="vue.min.js"></script>
    <script>
        var app = new Vue({
            el: '#app',
            // 定义局部组件，这里可以定义多个局部组件
             components: {
            //组件的名字
            'Navbar': {
            //组件的内容
            template: '<ul><li>首页</li><li>学员管理</li></ul>'
        }
    }
```
- 全局组件
```
// 定义全局组件
Vue.component('Navbar', {
    template: '<ul><li>首页</li><li>学员管理</li><li>讲师管理</li></ul>'
})
```
```

<div id="app">
    <Navbar></Navbar>
</div>
<script src="vue.min.js"></script>
<script src="components/Navbar.js"></script>
```
#### 生命周期
![avatar](src/main/resources/static/0.9177152660737906.png)

#### 路由
- vue-router.min.js
```
<div id="app">
    <h1>Hello App!</h1>
    <p>
        <router-link to="/">首页</router-link>
        <router-link to="/student">会员管理</router-link>
        <router-link to="/teacher">讲师管理</router-link>
    </p>
    <!-- 路由出口 -->
    <!-- 路由匹配到的组件将渲染在这里 -->
    <router-view></router-view>
</div>
<script>
    // 1. 定义（路由）组件。
    // 可以从其他文件 import 进来
    const Welcome = { template: '<div>欢迎</div>' }
    const Student = { template: '<div>student list</div>' }
    const Teacher = { template: '<div>teacher list</div>' }
    // 2. 定义路由
    // 每个路由应该映射一个组件。
    const routes = [
        { path: '/', redirect: '/welcome' }, //设置默认指向的路径
        { path: '/welcome', component: Welcome },
        { path: '/student', component: Student },
        { path: '/teacher', component: Teacher }
    ]
    // 3. 创建 router 实例，然后传 `routes` 配置
    const router = new VueRouter({
        routes // （缩写）相当于 routes: routes
    })
    // 4. 创建和挂载根实例。
    // 从而让整个应用都有路由功能
    const app = new Vue({
        el: '#app',
        router
    })
</script>
```