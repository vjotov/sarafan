import Vue from 'vue'

const profile = Vue.resource('/profile{/id}')

export default {
    get: id => profile.get({id}),
    changeSubscription: chennelId => Vue.http.post(`/profile/change-subscription/${chennelId}`)
}