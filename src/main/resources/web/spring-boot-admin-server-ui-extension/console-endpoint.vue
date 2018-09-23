<template>
  <div
    class="section console-view"
    :class="{ 'is-loading' : !hasLoaded }">
    <div
      v-if="error"
      class="message is-danger">
      <div class="message-body">
        <strong>
          <font-awesome-icon
            class="has-text-danger"
            icon="exclamation-triangle"/>
          Fetching logfile failed.
        </strong>
        <p v-text="error.message"/>
      </div>
    </div>
    <div
      class="console-view-actions"
      v-if="hasLoaded">
      <div class="field has-addons">
        <p class="control is-expanded">
          <input
            v-model="command"
            @keyup.enter="sendCommand"
            type="text"
            placeholder="Command"
            class="input">
        </p>
        <p class="control">
          <button
            @click="sendCommand"
            class="button">Send
          </button>
        </p>
      </div>
      <div class="card panel">
        <div class="card-content">
          <div id="consoleArea">
            <div
              class="logEntry"
              v-for="logEntry in logEntries"
              :key="logEntry.time">
              <div class="logBody">
                <div class="logTime">{{ logEntry.time }}</div>
                <div class="logSource">{{ logEntry.source }}</div>
                <div class="logMessage">{{ logEntry.message }}</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import AnsiUp from 'ansi_up';

  export default {
    props: {
      instance: {
        type: Object,
        required: true,
      },
    },
    data: () => ({
      command: '',
      hasLoaded: false,
      logEntries: [],
      ansiUp: new AnsiUp(),
    }),
    methods: {
      sendCommand: async function () {
        await this.instance.axios.post('actuator/console', {
          command: this.command,
        });

        this.getConsoleLog();
      },
      getConsoleLog: async function () {
        let consoleData = await this.instance.axios.get('actuator/console');

        let entries = [];
        consoleData.data.forEach(line => {
          const regex = /([0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}.[0-9]{3}) {2}(.*) {3}: (.*)/gm;

          let regexResult = regex.exec(line);

          if (regexResult === null) {
            return;
          }

          entries.push({
            time: this.ansiUp.ansi_to_html(regexResult[1]),
            source: this.ansiUp.ansi_to_html(regexResult[2]),
            message: this.ansiUp.ansi_to_html(regexResult[3]),
          });

          this.logEntries = entries;
        });
      },
    },
    async beforeMount() {
      await this.getConsoleLog();

      setInterval(this.getConsoleLog, 1000);

      this.hasLoaded = true;
    },
  };
</script>

<style>
  .logTime,
  .logSource,
  .logMessage {
    display: inline;
  }

  .logTime,
  .logSource {
    padding-right: 10px;
  }

  .logEntry {
    display: block;
    width: 100%;
    border-bottom: 1px solid #aaa;
  }

  #consoleArea {
    border: 1px solid #aaa;
    padding: 4px;
    font-family: Lucida Console, monospace;
    height: 100%;
    bottom: 64px;
    overflow: auto;
  }
</style>
