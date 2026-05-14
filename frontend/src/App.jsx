import { useState } from 'react'
import Login from './components/Login'
import Dashboard from './components/Dashboard'
import './index.css'

export default function App() {
  const [account, setAccount] = useState(null)

  return (
    <main className="app">
      <h1>ATM System</h1>

      {!account ? (
        <Login onLogin={setAccount} />
      ) : (
        <Dashboard account={account} onLogout={() => setAccount(null)} />
      )}
    </main>
  )
}