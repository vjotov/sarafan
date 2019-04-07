<template>
    <router-link
        :to="`/user/${user.id}`"
        class="px-3"
    >
        <v-avatar
            v-if="user && user.userpic"
            :size="`${size || 36}px`"
        >
            <img
                :src="user.userpic"
                :alt="user.name"
            >
        </v-avatar>
        <v-avatar
            v-else
            :size="`${size || 36}px`"
            color="indigo"
        >
            <v-icon dark>account_circle</v-icon>
        </v-avatar>
        <span>{{ userName }}</span>
    </router-link>
</template>
<script>
    import UserLink from 'components/UserLink.vue'

    export default {
        name:'UserLink',
        props: ['user', 'size'],
        computed: {
            userName() {
                return this.user ? this.user.name : "uknown"
            }
        },
    }
</script>

<style scoped>

</style>