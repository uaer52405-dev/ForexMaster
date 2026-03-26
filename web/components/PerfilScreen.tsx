'use client'
import { useState } from 'react'

interface Props { onNavigate: (screen: string) => void }

export default function PerfilScreen({ onNavigate }: Props) {
  const [notifs, setNotifs] = useState(true)

  return (
    <div className="screen-scroll pb-8">
      {/* Header */}
      <div className="grad-blue px-5 pt-8 pb-7">
        <div className="flex flex-col items-center">
          {/* Avatar */}
          <div className="relative mb-3">
            <div className="w-20 h-20 rounded-full bg-white/25 flex items-center justify-center text-white text-2xl font-bold">
              CG
            </div>
            <div className="absolute bottom-0 right-0 w-6 h-6 bg-[#F5A623] rounded-full flex items-center justify-center text-white text-xs">
              ✏️
            </div>
          </div>
          <p className="text-white text-lg font-bold">Carlos García</p>
          <p className="text-white/75 text-sm">carlos.garcia@email.com</p>

          {/* Stats */}
          <div className="flex items-center gap-6 mt-5 w-full justify-center">
            {[['8', 'Recargas'], ['$75.50', 'Enviado'], ['4', 'Contactos']].map(([v, l], i, arr) => (
              <div key={l} className="flex items-center gap-6">
                <div className="text-center">
                  <p className="text-white font-bold text-lg">{v}</p>
                  <p className="text-white/70 text-xs">{l}</p>
                </div>
                {i < arr.length - 1 && <div className="w-px h-8 bg-white/20"/>}
              </div>
            ))}
          </div>
        </div>
      </div>

      {/* Saldo card */}
      <div className="mx-5 mt-5 bg-white rounded-2xl px-4 py-4 flex items-center gap-4 shadow-sm">
        <div className="w-12 h-12 rounded-xl bg-cuba-blue-bg flex items-center justify-center text-2xl">💰</div>
        <div className="flex-1">
          <p className="text-gray-400 text-xs">Saldo disponible</p>
          <p className="text-cuba-navy text-2xl font-bold">$24.50 USD</p>
        </div>
        <button className="bg-cuba-blue text-white text-sm font-semibold px-4 py-2 rounded-xl">Añadir</button>
      </div>

      {/* Sección Mi cuenta */}
      <div className="px-5 mt-5">
        <p className="text-gray-400 text-xs font-semibold uppercase tracking-wide mb-2">Mi cuenta</p>
        <div className="bg-white rounded-2xl overflow-hidden shadow-sm">
          {[
            { emoji: '💳', bg: 'bg-cuba-blue-bg', label: 'Métodos de pago',  sub: '2 tarjetas guardadas' },
            { emoji: '👥', bg: 'bg-success-bg',   label: 'Mis contactos',    sub: '4 contactos en Cuba' },
            { emoji: '📋', bg: 'bg-amber-50',     label: 'Mis recargas',     sub: 'Ver historial completo' },
          ].map((item, i, arr) => (
            <div key={item.label}>
              <button className="w-full flex items-center gap-3 px-4 py-3.5 tap-card">
                <div className={`${item.bg} w-10 h-10 rounded-xl flex items-center justify-center text-xl`}>
                  {item.emoji}
                </div>
                <div className="flex-1 text-left">
                  <p className="text-cuba-navy text-sm font-medium">{item.label}</p>
                  <p className="text-gray-400 text-xs">{item.sub}</p>
                </div>
                <span className="text-gray-300 text-lg">›</span>
              </button>
              {i < arr.length - 1 && <div className="border-t border-gray-50 ml-14"/>}
            </div>
          ))}
        </div>
      </div>

      {/* Preferencias */}
      <div className="px-5 mt-4">
        <p className="text-gray-400 text-xs font-semibold uppercase tracking-wide mb-2">Preferencias</p>
        <div className="bg-white rounded-2xl overflow-hidden shadow-sm">
          {/* Notificaciones toggle */}
          <div className="flex items-center gap-3 px-4 py-3.5">
            <div className="bg-cuba-red-bg w-10 h-10 rounded-xl flex items-center justify-center text-xl">🔔</div>
            <div className="flex-1">
              <p className="text-cuba-navy text-sm font-medium">Notificaciones</p>
              <p className="text-gray-400 text-xs">Alertas de recargas</p>
            </div>
            <button
              onClick={() => setNotifs(!notifs)}
              className={`w-12 h-6 rounded-full transition-colors relative ${notifs ? 'bg-cuba-blue' : 'bg-gray-200'}`}
            >
              <div className={`w-5 h-5 bg-white rounded-full shadow absolute top-0.5 transition-transform ${notifs ? 'translate-x-6' : 'translate-x-0.5'}`}/>
            </button>
          </div>
          <div className="border-t border-gray-50 ml-14"/>
          <button className="w-full flex items-center gap-3 px-4 py-3.5 tap-card">
            <div className="bg-gray-100 w-10 h-10 rounded-xl flex items-center justify-center text-xl">🌐</div>
            <div className="flex-1 text-left">
              <p className="text-cuba-navy text-sm font-medium">Idioma</p>
              <p className="text-gray-400 text-xs">Español</p>
            </div>
            <span className="text-gray-300 text-lg">›</span>
          </button>
        </div>
      </div>

      {/* Soporte */}
      <div className="px-5 mt-4">
        <p className="text-gray-400 text-xs font-semibold uppercase tracking-wide mb-2">Soporte</p>
        <div className="bg-white rounded-2xl overflow-hidden shadow-sm">
          {[
            { emoji: '❓', label: 'Ayuda y FAQ' },
            { emoji: '💬', label: 'Contactar soporte' },
            { emoji: '⭐', label: 'Calificar la app' },
          ].map((item, i, arr) => (
            <div key={item.label}>
              <button className="w-full flex items-center gap-3 px-4 py-3.5 tap-card">
                <div className="bg-gray-100 w-10 h-10 rounded-xl flex items-center justify-center text-xl">
                  {item.emoji}
                </div>
                <p className="flex-1 text-cuba-navy text-sm font-medium text-left">{item.label}</p>
                <span className="text-gray-300 text-lg">›</span>
              </button>
              {i < arr.length - 1 && <div className="border-t border-gray-50 ml-14"/>}
            </div>
          ))}
        </div>
      </div>

      {/* Cerrar sesión */}
      <div className="px-5 mt-5">
        <button
          onClick={() => onNavigate('home')}
          className="w-full border-2 border-cuba-red text-cuba-red font-semibold py-3.5 rounded-2xl flex items-center justify-center gap-2 tap-card"
        >
          <span>🚪</span> Cerrar sesión
        </button>
      </div>

      <p className="text-center text-gray-300 text-xs mt-4">CubaRecarga v1.0.0</p>
    </div>
  )
}
