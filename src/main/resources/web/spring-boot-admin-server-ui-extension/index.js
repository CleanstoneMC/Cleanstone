import consoleEndpoint from './console-endpoint';

SBA.use({
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
    }
});
