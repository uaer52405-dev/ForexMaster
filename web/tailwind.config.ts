import type { Config } from 'tailwindcss'

const config: Config = {
  content: [
    './pages/**/*.{js,ts,jsx,tsx,mdx}',
    './components/**/*.{js,ts,jsx,tsx,mdx}',
    './app/**/*.{js,ts,jsx,tsx,mdx}',
  ],
  theme: {
    extend: {
      colors: {
        cuba: {
          blue:        '#0B3D8C',
          'blue-dark': '#072966',
          'blue-light':'#3A6FD8',
          'blue-bg':   '#D6E4FF',
          red:         '#CF0A0A',
          'red-light': '#FF4D4D',
          'red-bg':    '#FFdad6',
          gold:        '#F5A623',
          navy:        '#0D1B40',
        },
        success: {
          DEFAULT: '#27AE60',
          bg:      '#D4F5E4',
        },
      },
      fontFamily: {
        sans: ['Inter', 'system-ui', 'sans-serif'],
      },
    },
  },
  plugins: [],
}

export default config
