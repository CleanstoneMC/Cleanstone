<template>
    <div class="cli">
        <span v-text="log"/>
    </div>
</template>

<script>
    export default {
        props: {
            instance: {
                type: Object,
                required: true
            }
        },
        data: () => ({
            log: ''
        }),
        async created() {
            const response = await this.instance.axios.get('actuator/cli'); //<2>
            this.log = response.data;
        }
    };
</script>

<style>
    .cli {
        font-size: 20px;
        width: 80%;
    }
</style>