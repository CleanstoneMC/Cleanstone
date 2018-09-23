import consoleEndpoint from './console-endpoint';
import beansEndpoint from './beans-endpoint';

window.SBA.use({
  install({viewRegistry}) {
    viewRegistry.addView({
      name: 'instances/console',
      parent: 'instances',
      path: 'console',
      component: consoleEndpoint,
      label: 'Console',
      order: 1000,
      isEnabled: ({instance}) => instance.hasEndpoint('console')
    });

    viewRegistry.addView({
      name: 'instances/beans',
      parent: 'instances',
      path: 'beans',
      component: beansEndpoint,
      label: 'Beans',
      group: 'Insights',
      order: 1000,
      isEnabled: ({instance}) => instance.hasEndpoint('beans')
    });
  }
});
