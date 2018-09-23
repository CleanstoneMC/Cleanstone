<template>
  <section
    class="section"
    :class="{ 'is-loading' : !hasLoaded }">
    <div
      v-if="error"
      class="message is-danger">
      <div class="message-body">
        <strong>
          <font-awesome-icon
            class="has-text-danger"
            icon="exclamation-triangle"/>
          Fetching beans failed.
        </strong>
        <p v-text="error.message"/>
      </div>
    </div>
    <sba-panel
      :header-sticks-below="['#navigation']"
      v-for="(beanDefinition, beanName) in beans"
      :key="beanName"
      :title="beanName">
      <table class="table is-fullwidth">
        <tr
          v-for="(value, key) in beanDefinition"
          :key="`${key}-${value}`">
          <td>
            <span v-text="key"/><br>
            <small
              class="is-muted"
              v-if="value"
              v-text="value"/>
            <p
              class="is-muted"
              v-else>No Beans set</p>
          </td>
        </tr>
      </table>
    </sba-panel>
  </section>
</template>

<script>
  export default {
    props: {
      instance: {
        type: Object,
        required: true,
      },
    },
    data: () => ({
      hasLoaded: false,
      error: null,
      beans: null,
    }),
    created() {
      this.fetchBeans();
    },
    methods: {
      async fetchBeans() {
        this.error = null;
        try {
          const res = await this.instance.axios.get('actuator/beans');
          this.beans = res.data.contexts.application.beans;
        } catch (error) {
          this.error = error;
        }
        this.hasLoaded = true;
      },
    },
  };
</script>
