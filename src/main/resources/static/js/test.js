var app = new Vue({
    el: '#app',
    data: {
        message: 'Hello Vue!'
    }
})
var app2 = new Vue({
    el: '#app-2',
    data: {
        message: 'You loaded this page on ' + new Date()
    }
})
var app3 = new Vue({
    el: '#app-3',
    data: {
        seen: true
    }
})
var app4 = new Vue({
    el: '#app-4',
    data: {
        todos: [
            { text: 'Learn JavaScript' },
            { text: 'Learn Vue' },
            { text: 'Build something awesome' }
        ]
    }
})
var app5 = new Vue({
    el: '#app-5',
    data: {
        message: '这是一个按钮'
    },
    methods: {
        reverseMessage: function () {
            if (this.message.startsWith('this')) {
                this.message = '这是一个按钮'
            } else {
                this.message = 'this is a button'
            }
        }
    }
})
var app6 = new Vue({
    el: '#app-6',
    data: {
        message: 'Hello vue'
    }
})

Vue.component('todo-item', {
    props: ['item'],
    template: '<li>{{ item.text }}</li>'
})
var app7 = new Vue({
    el: '#app-7',
    data: {
        liList: [
            { text: 'Chelsea' },
            { text: 'Liverpool' },
            { text: 'West Ham' }
        ]
    }
})

var app8 = new Vue({
    el: '#app-8',
    data: {
        checked: false
    }
})

var app9 = new Vue({
    el: '#app-9',
    data: {
        checkedNames: []
    }
})

var app10 = new Vue({
    el: "#app-10",
    data: {
        selected: 1,
        obj: [
            {id: 1, name: 'jquery'},
            {id: 2, name: 'vue'},
            {id: 3, name: 'angular'},
            {id: 4, name: 'react'}
        ]
    }
})