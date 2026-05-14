import { useState } from 'react'
import TransactionForm from './TransactionForm'
import { deposit, withdraw } from '../api/accountApi'

export default function Dashboard({ account, onLogout }) {
  const [balance, setBalance] = useState(account.balance)
  const [message, setMessage] = useState('')
  const [error, setError] = useState('')

  async function handleDeposit(amount) {
    setMessage('')
    setError('')

    try {
      const newBalance = await deposit(account.accountNumber, amount)
      setBalance(newBalance)
      setMessage('Insättning lyckades.')
    } catch {
      setError('Insättning misslyckades.')
    }
  }

  async function handleWithdraw(amount) {
    setMessage('')
    setError('')

    try {
      const newBalance = await withdraw(account.accountNumber, amount)
      setBalance(newBalance)
      setMessage('Uttag lyckades.')
    } catch {
      setError('Uttag misslyckades. Kontrollera saldot.')
    }
  }

  return (
    <div className="card">
      <h2>Dashboard</h2>

      <p><strong>Kontonummer:</strong> {account.accountNumber}</p>
      <p className="balance">Saldo: {balance} kr</p>

      <div className="forms">
        <TransactionForm title="Insättning" buttonText="Sätt in" onSubmit={handleDeposit} />
        <TransactionForm title="Uttag" buttonText="Ta ut" onSubmit={handleWithdraw} />
      </div>

      {message && <p className="success">{message}</p>}
      {error && <p className="error">{error}</p>}

      <button className="logout" onClick={onLogout}>Logga ut</button>
    </div>
  )
}