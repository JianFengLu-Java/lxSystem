/** @type {import('tailwindcss').Config} */
module.exports = {
    content: [
        "./src/**/*.{js,ts,jsx,tsx}", // ✅ 你项目中需要扫描的文件路径
    ],
    theme: {
        extend: {
            colors: {
                primary: '#1E3A8A', // 自定义颜色
                brand: {
                    light: '#3ABFF8',
                    dark: '#1E40AF',
                },
            },
            fontFamily: {
                sans: ['Inter', 'sans-serif'], // 自定义字体
            },
        },
    },
    plugins: [],
}