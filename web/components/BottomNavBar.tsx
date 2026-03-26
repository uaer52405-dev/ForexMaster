'use client'

type Screen = 'home' | 'recargar' | 'historial' | 'perfil'

interface Props {
  current: Screen
  onNavigate: (s: Screen) => void
}

const NAV = [
  { id: 'home'     as Screen, label: 'Inicio',    icon: '🏠' },
  { id: 'recargar' as Screen, label: 'Recargar',  icon: '📱' },
  { id: 'historial'as Screen, label: 'Historial', icon: '📋' },
  { id: 'perfil'   as Screen, label: 'Perfil',    icon: '👤' },
]

export default function BottomNavBar({ current, onNavigate }: Props) {
  return (
    <nav className="h-16 bg-white border-t border-gray-100 flex items-center safe-bottom">
      {NAV.map(item => {
        const active = current === item.id
        return (
          <button
            key={item.id}
            onClick={() => onNavigate(item.id)}
            className="flex-1 flex flex-col items-center justify-center gap-0.5 py-1 tap-card"
          >
            <div className={`w-10 h-8 rounded-xl flex items-center justify-center text-lg transition-all ${
              active ? 'bg-cuba-blue-bg' : ''
            }`}>
              {item.icon}
            </div>
            <span className={`text-[10px] font-semibold transition-colors ${
              active ? 'text-cuba-blue' : 'text-gray-400'
            }`}>
              {item.label}
            </span>
          </button>
        )
      })}
    </nav>
  )
}
