'use client'
import { useState } from 'react'
import BottomNavBar  from '@/components/BottomNavBar'
import HomeScreen    from '@/components/HomeScreen'
import RecargarScreen from '@/components/RecargarScreen'
import HistorialScreen from '@/components/HistorialScreen'
import PerfilScreen  from '@/components/PerfilScreen'

type Screen = 'home' | 'recargar' | 'historial' | 'perfil'

const TITLES: Record<Screen, string> = {
  home:      'CubaRecarga',
  recargar:  'Nueva Recarga',
  historial: 'Historial',
  perfil:    'Mi Perfil',
}

export default function App() {
  const [screen, setScreen] = useState<Screen>('home')

  const navigate = (s: string) => {
    if (['home','recargar','historial','perfil'].includes(s)) {
      setScreen(s as Screen)
    }
  }

  return (
    /* Contenedor mobile centrado – se ve bien en escritorio también */
    <div className="min-h-screen bg-gray-100 flex items-center justify-center">
      <div className="w-full max-w-sm min-h-screen bg-[#F5F7FF] flex flex-col shadow-2xl relative overflow-hidden">
        {/* Barra de estado simulada */}
        <div className="grad-blue h-6 flex items-center justify-between px-5 shrink-0">
          <span className="text-white/80 text-[10px] font-medium">9:41</span>
          <div className="flex items-center gap-1.5">
            <span className="text-white text-[10px]">●●●</span>
            <span className="text-white text-[10px]">WiFi</span>
            <span className="text-white text-[10px]">🔋</span>
          </div>
        </div>

        {/* Contenido principal */}
        <main className="flex-1 overflow-hidden">
          {screen === 'home'      && <HomeScreen     onNavigate={navigate} />}
          {screen === 'recargar'  && <RecargarScreen />}
          {screen === 'historial' && <HistorialScreen />}
          {screen === 'perfil'    && <PerfilScreen   onNavigate={navigate} />}
        </main>

        {/* Bottom Nav */}
        <BottomNavBar current={screen} onNavigate={setScreen} />
      </div>
    </div>
  )
}
