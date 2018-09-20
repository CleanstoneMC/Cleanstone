import customEndpoint from './cli-endpoint';

SBA.use({
    install({viewRegistry}) {
        viewRegistry.addView({
            name: 'instances/cli',
            parent: 'instances', // <1>
            path: 'cli',
            component: customEndpoint,
            label: 'CLI',
            order: 1000,
            isEnabled: ({instance}) => instance.hasEndpoint('cli') // <2>
        });
    }
});
