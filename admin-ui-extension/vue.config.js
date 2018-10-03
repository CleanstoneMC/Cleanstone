module.exports = {
    outputDir: 'dist',
    filenameHashing: false,
    chainWebpack: config => {
        config.entryPoints.delete('app');
        config.entry('cleanstone').add('./src/index.js');
        config.externals({
            vue: {
                commonjs: 'vue',
                commonjs2: 'vue',
                root: 'Vue'
            }
        });
        config.output.libraryTarget('var');
        config.optimization.splitChunks(false);
        config.module
            .rule('vue')
            .use('vue-loader')
            .loader('vue-loader')
            .tap(options => ({
                ...options,
                hotReload: false
            }));
        config.plugins.delete('html');
        config.plugins.delete('preload');
        config.plugins.delete('prefetch');
    }
};
