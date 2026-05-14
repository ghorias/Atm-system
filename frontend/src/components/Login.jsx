import { useState } from 'react'
import { login, getBalance } from '../api/accountApi'

export default function Login({ onLogin }) {
  const [accountNumber, setAccountNumber] = useState('')
  const [pin, setPin] = useState('')
  const [error, setError] = useState('')

  async function handleSubmit(e) {
    e.preventDefault()
    setError('')

    try {
      const ok = await login(accountNumber, pin)

      if (!ok) {
        setError('Fel kontonummer eller PIN.')
        return
      }

      const balance = await getBalance(accountNumber)
      onLogin({ accountNumber, balance })
    } catch {
      setError('Kunde inte ansluta till backend.')
    }
  }

  return (
    <div className="card">
      <h2>Logga in</h2>

      <form onSubmit={handleSubmit}>
        <label>Kontonummer</label>
        <input
          value={accountNumber}
          onChange={(e) => setAccountNumber(e.target.value)}
          placeholder="Ex: 12345"
        />

        <label>PIN</label>
        <input
          type="password"
          value={pin}
          onChange={(e) => setPin(e.target.value)}
          placeholder="PIN"
        />

        <button type="submit">Logga in</button>
      </form>

      {error && <p className="error">{error}</p>}
    </div>
  )
}