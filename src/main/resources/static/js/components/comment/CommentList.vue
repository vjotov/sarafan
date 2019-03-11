<template>
    <v-list two-line>
        <v-subheader>Comments</v-subheader>
        <template
            v-for="(item, index) in comments"
        >
            <v-divider
                v-if="index > 0"
                :key="index"
            ></v-divider>
            <comment-item
                :comment="item"
                :key="'item' + index"
            ></comment-item>
        </template>

        <comment-form :message-id="messageId" ></comment-form>
    </v-list two-line>
</template>

<script>
    import CoomentItem from './CommentItem.vue'
    import CoomentForm from './CommentForm.vue'

    export default {
        name: 'CommentList',
        props: ['comments', 'messageId'],
    }
</script>

<style scoped>

</style>
