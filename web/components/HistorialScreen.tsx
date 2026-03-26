'use client'
import { useState } from 'react'

type Estado = 'COMPLETADA' | 'PENDIENTE' | 'FALLIDA'

const TRANS = [
  { id: 1, numero: '+53 5 123 4567', tipo: 'Cubacel',   monto: '$10.00', fecha: 'Hoy, 9:15 AM',     estado: 'COMPLETADA' as Estado },
  { id: 2, numero: '+53 5 987 6543', tipo: 'Nauta',     monto: '$5.00',  fecha: 'Hoy, 8:02 AM',     estado: 'COMPLETADA' as Estado },
  { id: 3, numero: '+53 5 444 2233', tipo: 'Paquete B', monto: '$15.00', fecha: 'Ayer, 6:45 PM',    estado: 'PENDIENTE'  as Estado },
  { id: 4, numero: '+53 5 111 9988', tipo: 'Cubacel',   monto: '$20.00', fecha: 'Ayer, 2:10 PM',    estado: 'COMPLETADA' as Estado },
  { id: 5, numero: '+53 5 777 3322', tipo: 'Nauta',     monto: '$3.50',  fecha: '23 Mar, 11:30 AM', estado: 'FALLIDA'    as Estado },
  { id: 6, numero: '+53 5 555 0011', tipo: 'Paquete A', monto: '$7.00',  fecha: '22 Mar, 4:00 PM',  estado: 'COMPLETADA' as Estado },
  { id: 7, numero: '+53 5 234 5678', tipo: 'Cubacel',   monto: '$10.00', fecha: '20 Mar, 9:00 AM',  estado: 'COMPLETADA' as Estado },
  { id: 8, numero: '+53 5 876 5432', tipo: 'Cubacel',   monto: '$5.00',  fecha: '18 Mar, 3:30 PM',  estado: 'COMPLETADA' as Estado },
]

const estadoStyle: Record<Estado, { dot: string; bg: string; text: string; label: string }> = {
  COMPLETADA: { dot: 'bg-success',     bg: 'bg-success-bg',   text: 'text-success',   label: 'Completada' },
  PENDIENTE:  { dot: 'bg-cuba-gold',   bg: 'bg-amber-50',     text: 'text-cuba-gold', label: 'Pendiente'  },
  FALLIDA:    { dot: 'bg-cuba-red',    bg: 'bg-cuba-red-bg',  text: 'text-cuba-red',  label: 'Fallida'    },
}

const tipoEmoji: Record<string, string> = {
  Nauta: '🌐',
}

export default function HistorialScreen() {
  const [filtro, setFiltro] = useState('Todas')
  const filtros = ['Todas', 'Completadas', 'Pendientes', 'Fallidas']

  const lista = TRANS.filter(t => {
    if (filtro === 'Completadas') return t.estado === 'COMPLETADA'
    if (filtro === 'Pendientes')  return t.estado === 'PENDIENTE'
    if (filtro === 'Fallidas')    return t.estado === 'FALLIDA'
    return true
  })

  return (
    <div className="screen-scroll pb-4">
      {/* Header */}
      <div className="grad-blue px-5 pt-8 pb-6">
        <p className="text-white text-xl font-bold">Historial</p>
        <p className="text-white/75 text-sm">{TRANS.length} transacciones</p>

        <div className="grid grid-cols-3 gap-2 mt-4">
          {[['$75.50', 'Total enviado'], ['8', 'Recargas'], ['4', 'Contactos']].map(([v, l]) => (
            <div key={l} className="bg-white/15 rounded-xl py-3 text-center">
              <p className="text-white font-bold text-lg">{v}</p>
              <p className="text-white/70 text-[11px]">{l}</p>
            </div>
          ))}
        </div>
      </div>

      {/* Filtros */}
      <div className="px-5 mt-4 flex gap-2 overflow-x-auto pb-1 scrollbar-hide">
        {filtros.map(f => (
          <button
            key={f}
            onClick={() => setFiltro(f)}
            className={`whitespace-nowrap px-4 py-1.5 rounded-full text-xs font-semibold transition-all ${
              filtro === f
                ? 'bg-cuba-blue text-white'
                : 'bg-white text-gray-500 border border-gray-100'
            }`}
          >
            {f}
          </button>
        ))}
      </div>

      {/* Lista */}
      <div className="px-5 mt-3 space-y-2">
        {lista.length === 0 && (
          <div className="text-center text-gray-400 py-16">
            <p className="text-4xl mb-3">🔍</p>
            <p className="font-medium">Sin resultados</p>
          </div>
        )}
        {lista.map(tx => {
          const s = estadoStyle[tx.estado]
          return (
            <div key={tx.id} className="bg-white rounded-xl px-4 py-3 flex items-center gap-3">
              <div className="w-11 h-11 rounded-xl bg-cuba-blue-bg flex items-center justify-center text-xl">
                {tipoEmoji[tx.tipo] ?? '📱'}
              </div>
              <div className="flex-1 min-w-0">
                <p className="text-cuba-navy text-sm font-semibold truncate">{tx.numero}</p>
                <p className="text-gray-400 text-xs">{tx.tipo} · {tx.fecha}</p>
              </div>
              <div className="text-right shrink-0">
                <p className="text-cuba-navy text-sm font-bold mb-1">{tx.monto}</p>
                <span className={`${s.bg} ${s.text} text-[10px] font-medium px-2 py-0.5 rounded-md`}>
                  {s.label}
                </span>
              </div>
            </div>
          )
        })}
      </div>
    </div>
  )
}
