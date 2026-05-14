export async function login(accountNumber, pin) {
  const response = await fetch('/api/login', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ accountNumber, pin })
  })

  if (!response.ok) throw new Error('Login request failed')
  return await response.json()
}

export async function getBalance(accountNumber) {
  const response = await fetch(`/api/account/${accountNumber}/balance`)
  if (!response.ok) throw new Error('Could not fetch balance')
  return await response.json()
}

export async function deposit(accountNumber, amount) {
  const response = await fetch(`/api/account/${accountNumber}/deposit`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ amount })
  })

  if (!response.ok) throw new Error('Deposit failed')
  return await response.json()
}

export async function withdraw(accountNumber, amount) {
  const response = await fetch(`/api/account/${accountNumber}/withdraw`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ amount })
  })

  if (!response.ok) throw new Error('Withdraw failed')
  return await response.json()
}