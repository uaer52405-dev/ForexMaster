'use client'
import { useState } from 'react'

const PLANES = {
  cubacel: [
    { id: 'c1', nombre: 'Básica',   precio: '$5.00',  desc: '25 CUC + bono\n30 días',   popular: false },
    { id: 'c2', nombre: 'Estándar', precio: '$10.00', desc: '60 CUC + bono\n30 días',   popular: true  },
    { id: 'c3', nombre: 'Plus',     precio: '$20.00', desc: '130 CUC + bono\n30 días',  popular: false },
    { id: 'c4', nombre: 'Premium',  precio: '$30.00', desc: '200 CUC + bono\n30 días',  popular: false },
  ],
  nauta: [
    { id: 'n1', nombre: '1 hora',   precio: '$1.50',  desc: 'Nauta Hogar\n1 h navegación',  popular: false },
    { id: 'n2', nombre: '3 horas',  precio: '$3.50',  desc: 'Nauta Hogar\n3 h navegación',  popular: true  },
    { id: 'n3', nombre: '10 horas', precio: '$10.00', desc: 'Nauta Hogar\n10 h navegación', popular: false },
  ],
  paquetes: [
    { id: 'p1', nombre: 'Pack A', precio: '$7.00',  desc: '300 MB datos\n60 CUC voz',   popular: false },
    { id: 'p2', nombre: 'Pack B', precio: '$15.00', desc: '1 GB datos\n130 CUC voz',    popular: true  },
    { id: 'p3', nombre: 'Pack C', precio: '$25.00', desc: '2.5 GB datos\n200 CUC voz',  popular: false },
  ],
}

type Tab = 'cubacel' | 'nauta' | 'paquetes'

export default function RecargarScreen() {
  const [tab, setTab]         = useState<Tab>('cubacel')
  const [phone, setPhone]     = useState('')
  const [planId, setPlanId]   = useState<string | null>(null)
  const [confirm, setConfirm] = useState(false)

  const planes = PLANES[tab]
  const plan   = planes.find(p => p.id === planId) ?? null
  const canSend = phone.length >= 7 && plan !== null

  return (
    <div className="screen-scroll pb-6">
      {/* Header */}
      <div className="grad-blue px-5 pt-8 pb-6">
        <p className="text-white text-xl font-bold">Nueva Recarga</p>
        <p className="text-white/75 text-sm">Envía crédito a Cuba al instante</p>
      </div>

      {/* Número */}
      <div className="px-5 mt-5">
        <label className="text-cuba-navy text-sm font-semibold block mb-2">Número en Cuba</label>
        <div className="flex items-center gap-2 bg-white border border-gray-200 rounded-xl px-4 py-3 focus-within:border-cuba-blue transition-colors">
          <span className="text-xl">🇨🇺</span>
          <span className="text-cuba-navy font-semibold text-sm">+53</span>
          <div className="w-px h-5 bg-gray-200 mx-1"/>
          <input
            type="tel"
            value={phone}
            onChange={e => setPhone(e.target.value.replace(/\D/g, '').slice(0, 11))}
            placeholder="5 XXX XXXX"
            className="flex-1 outline-none text-sm text-cuba-navy placeholder-gray-300 bg-transparent"
          />
          {phone && (
            <button onClick={() => setPhone('')} className="text-gray-300 hover:text-gray-500">✕</button>
          )}
        </div>
      </div>

      {/* Tabs */}
      <div className="px-5 mt-5">
        <label className="text-cuba-navy text-sm font-semibold block mb-2">Tipo de recarga</label>
        <div className="bg-gray-100 rounded-xl p-1 flex gap-1">
          {(['cubacel','nauta','paquetes'] as Tab[]).map(t => (
            <button
              key={t}
              onClick={() => { setTab(t); setPlanId(null) }}
              className={`flex-1 py-2.5 rounded-lg text-xs font-semibold transition-all capitalize ${
                tab === t ? 'bg-cuba-blue text-white shadow-sm' : 'text-gray-500'
              }`}
            >
              {t === 'cubacel' ? 'Cubacel' : t === 'nauta' ? 'Nauta' : 'Paquetes'}
            </button>
          ))}
        </div>
      </div>

      {/* Planes */}
      <div className="px-5 mt-5">
        <label className="text-cuba-navy text-sm font-semibold block mb-3">Selecciona un plan</label>
        <div className="grid grid-cols-2 gap-3">
          {planes.map(p => (
            <button
              key={p.id}
              onClick={() => setPlanId(p.id)}
              className={`relative rounded-2xl p-4 text-center border-2 transition-all tap-card ${
                planId === p.id
                  ? 'border-cuba-blue bg-cuba-blue-bg shadow-md'
                  : 'border-gray-100 bg-white'
              }`}
            >
              {p.popular && (
                <span className="absolute top-0 right-0 bg-cuba-red text-white text-[9px] font-bold px-2 py-1 rounded-bl-xl rounded-tr-2xl">
                  Popular
                </span>
              )}
              <p className={`text-xs font-semibold mb-1 ${planId === p.id ? 'text-cuba-blue-dark' : 'text-gray-600'}`}>
                {p.nombre}
              </p>
              <p className={`text-2xl font-extrabold mb-1 ${planId === p.id ? 'text-cuba-blue' : 'text-cuba-navy'}`}>
                {p.precio}
              </p>
              <p className="text-[11px] text-gray-400 whitespace-pre-line leading-tight">{p.desc}</p>
              {planId === p.id && <p className="text-cuba-blue text-base mt-1">✓</p>}
            </button>
          ))}
        </div>
      </div>

      {/* Botón */}
      <div className="px-5 mt-6">
        <button
          disabled={!canSend}
          onClick={() => setConfirm(true)}
          className={`w-full h-14 rounded-2xl font-bold text-white text-base flex items-center justify-center gap-2 transition-all ${
            canSend ? 'bg-cuba-blue active:scale-95' : 'bg-gray-200 text-gray-400 cursor-not-allowed'
          }`}
        >
          ⚡ {plan ? `Recargar ${plan.precio}` : 'Recargar ahora'}
        </button>
      </div>

      {/* Modal confirmación */}
      {confirm && plan && (
        <div className="fixed inset-0 bg-black/50 flex items-end z-50" onClick={() => setConfirm(false)}>
          <div className="bg-white w-full rounded-t-3xl p-6" onClick={e => e.stopPropagation()}>
            <div className="w-10 h-1 bg-gray-200 rounded-full mx-auto mb-5"/>
            <div className="flex flex-col items-center mb-5">
              <div className="w-14 h-14 rounded-full bg-cuba-blue-bg flex items-center justify-center text-3xl mb-3">⚡</div>
              <p className="text-cuba-navy text-lg font-bold">Confirmar recarga</p>
            </div>
            <div className="space-y-3 mb-5">
              {[
                ['Número',       `+53 ${phone}`],
                ['Plan',         plan.nombre],
                ['Monto',        plan.precio],
              ].map(([k, v]) => (
                <div key={k} className="flex justify-between">
                  <span className="text-gray-400 text-sm">{k}</span>
                  <span className="text-cuba-navy text-sm font-medium">{v}</span>
                </div>
              ))}
              <div className="border-t pt-3 flex justify-between">
                <span className="text-gray-400 text-sm">Total a pagar</span>
                <span className="text-cuba-navy text-sm font-bold">{plan.precio}</span>
              </div>
            </div>
            <button
              onClick={() => { setConfirm(false); setPlanId(null); setPhone('') }}
              className="w-full h-13 bg-cuba-blue text-white font-bold rounded-2xl py-4 mb-3"
            >
              Confirmar y recargar
            </button>
            <button onClick={() => setConfirm(false)} className="w-full text-gray-400 text-sm py-2">
              Cancelar
            </button>
          </div>
        </div>
      )}
    </div>
  )
}
