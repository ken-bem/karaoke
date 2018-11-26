import Vue from 'vue'
import { VueGoodTable } from 'vue-good-table';
import 'vue-good-table/dist/vue-good-table.css'

new Vue({
    el: "#app",
    components:{'vue-good-table': VueGoodTable},
    data(){
        return {
            msg: "Test",
            cursos: []
        }
    }
});