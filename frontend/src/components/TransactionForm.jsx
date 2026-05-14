import { useState } from 'react'

export default function TransactionForm({ title, buttonText, onSubmit }) {
  const [amount, setAmount] = useState('')
  const [error, setError] = useState('')

  async function handleSubmit(e) {
    e.preventDefault()
    setError('')

    const value = Number(amount)

    if (!value || value <= 0) {
      setError('Beloppet måste vara större än 0.')
      return
    }

    await onSubmit(value)
    setAmount('')
  }

  return (
    <form className="transaction-form" onSubmit={handleSubmit}>
      <h3>{title}</h3>

      <input
        type="number"
        value={amount}
        onChange={(e) => setAmount(e.target.value)}
        placeholder="Belopp"
      />

      <button type="submit">{buttonText}</button>

      {error && <p className="error">{error}</p>}
    </form>
  )
}