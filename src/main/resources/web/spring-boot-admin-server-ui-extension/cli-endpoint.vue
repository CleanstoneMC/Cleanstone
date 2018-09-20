<template>
    <div class="cli">
        <input v-model="command" title="Command"/>
        <button v-on:click="sendCommand">Send</button>
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
            command: ''
        }),
        methods: {
            sendCommand: async function () {
                await this.instance.axios.post('actuator/cli', {
                    command: this.command
                })
            }
        }
    };
</script>

<style>
    .cli {
        font-size: 20px;
        width: 80%;
    }
</style>