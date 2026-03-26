'use client'

interface Props {
  onNavigate: (screen: string) => void
}

const recientes = [
  { numero: '+53 5 123 4567', tipo: 'Cubacel', monto: '$10.00', fecha: 'Hoy, 9:15 AM' },
  { numero: '+53 5 987 6543', tipo: 'Nauta',   monto: '$5.00',  fecha: 'Ayer, 3:42 PM' },
  { numero: '+53 5 555 0011', tipo: 'Pack B',  monto: '$15.00', fecha: '23 Mar, 11:00 AM' },
]

export default function HomeScreen({ onNavigate }: Props) {
  return (
    <div className="screen-scroll pb-4">
      {/* Header */}
      <div className="grad-blue px-5 pt-8 pb-6">
        <div className="flex items-center justify-between mb-5">
          <div>
            <p className="text-white text-xl font-bold">¡Hola, Carlos!</p>
            <p className="text-white/75 text-sm">¿A quién recargas hoy?</p>
          </div>
          <button
            onClick={() => onNavigate('perfil')}
            className="w-11 h-11 rounded-full bg-white/20 flex items-center justify-center"
          >
            <svg className="w-6 h-6 text-white" fill="currentColor" viewBox="0 0 24 24">
              <path d="M12 12c2.7 0 4.8-2.1 4.8-4.8S14.7 2.4 12 2.4 7.2 4.5 7.2 7.2 9.3 12 12 12zm0 2.4c-3.2 0-9.6 1.6-9.6 4.8v2.4h19.2v-2.4c0-3.2-6.4-4.8-9.6-4.8z"/>
            </svg>
          </button>
        </div>

        {/* Saldo card */}
        <div className="bg-white/15 rounded-2xl px-5 py-4 flex items-center justify-between">
          <div>
            <p className="text-white/70 text-xs mb-1">Saldo disponible</p>
            <p className="text-white text-3xl font-bold">$24.50 USD</p>
          </div>
          <button className="bg-[#F5A623] text-white text-sm font-bold px-4 py-2 rounded-xl flex items-center gap-1">
            <svg className="w-4 h-4" fill="currentColor" viewBox="0 0 24 24"><path d="M19 13H13v6h-2v-6H5v-2h6V5h2v6h6v2z"/></svg>
            Añadir
          </button>
        </div>
      </div>

      {/* Servicios */}
      <div className="px-5 mt-6">
        <p className="text-cuba-navy font-bold text-lg mb-4">Servicios</p>
        <div className="grid grid-cols-4 gap-3">
          {[
            { label: 'Cubacel', bg: 'bg-cuba-blue-bg', icon: '📱', color: 'text-cuba-blue', screen: 'recargar' },
            { label: 'Nauta',   bg: 'bg-cuba-red-bg',  icon: '🌐', color: 'text-cuba-red',  screen: 'recargar' },
            { label: 'Paquetes',bg: 'bg-amber-50',     icon: '📦', color: 'text-cuba-gold', screen: 'recargar' },
            { label: 'Contactos',bg:'bg-success-bg',   icon: '👥', color: 'text-success',   screen: 'perfil' },
          ].map((s) => (
            <button key={s.label} onClick={() => onNavigate(s.screen)} className="flex flex-col items-center gap-2 tap-card">
              <div className={`${s.bg} w-14 h-14 rounded-2xl flex items-center justify-center text-2xl`}>
                {s.icon}
              </div>
              <span className="text-xs font-medium text-cuba-navy">{s.label}</span>
            </button>
          ))}
        </div>
      </div>

      {/* Banner promo */}
      <div className="mx-5 mt-6 grad-red rounded-2xl p-5 flex items-center gap-4">
        <div className="flex-1">
          <p className="text-white/80 text-xs font-medium mb-1">¡Promo del mes!</p>
          <p className="text-white text-lg font-bold leading-snug">5% extra en<br/>recargas Cubacel</p>
          <button
            onClick={() => onNavigate('recargar')}
            className="mt-3 bg-white text-cuba-red text-xs font-bold px-4 py-1.5 rounded-lg"
          >
            Aprovechar
          </button>
        </div>
        <span className="text-6xl opacity-30 select-none">🏷️</span>
      </div>

      {/* Últimas recargas */}
      <div className="px-5 mt-6">
        <div className="flex items-center justify-between mb-3">
          <p className="text-cuba-navy font-bold text-lg">Últimas recargas</p>
          <button onClick={() => onNavigate('historial')} className="text-cuba-blue text-sm font-semibold">Ver todo</button>
        </div>
        <div className="space-y-2">
          {recientes.map((r) => (
            <div key={r.numero} className="bg-white rounded-xl px-4 py-3 flex items-center gap-3">
              <div className="w-10 h-10 rounded-full bg-cuba-blue-bg flex items-center justify-center text-lg">📱</div>
              <div className="flex-1 min-w-0">
                <p className="text-cuba-navy text-sm font-semibold truncate">{r.numero}</p>
                <p className="text-gray-400 text-xs">{r.tipo}</p>
              </div>
              <div className="text-right">
                <p className="text-cuba-navy text-sm font-bold">{r.monto}</p>
                <span className="text-success text-xs font-medium bg-success-bg px-2 py-0.5 rounded-md">Enviada</span>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  )
}
