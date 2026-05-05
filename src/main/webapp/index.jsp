<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>JavaQuest: Concept Conquest</title>
  <link rel="stylesheet" href="css/main.css"/>
  <link rel="stylesheet" href="css/animations.css"/>
  <style>
    /* ── WELCOME SCREEN LAYOUT ── */
    .welcome-wrapper {
      min-height: 100vh;
      display: flex;
      align-items: center;
      justify-content: center;
      position: relative;
      overflow: hidden;
    }

    /* ── WELCOME CARD ── */
    .welcome-card {
      background: var(--bg-panel);
      border: 1px solid var(--border);
      border-radius: 16px;
      padding: 48px 52px;
      width: 100%;
      max-width: 480px;
      text-align: center;
      position: relative;
      z-index: 10;
      animation: welcomeSlideUp 0.6s ease-out forwards;
    }

    .game-logo {
      font-family: 'Fira Code', monospace;
      font-size: 1.05rem;
      color: var(--accent-gold);
      letter-spacing: 2px;
      text-transform: uppercase;
      margin-bottom: 10px;
    }

    .game-title {
      font-size: 2.2rem;
      font-weight: 700;
      color: var(--text-primary);
      line-height: 1.2;
      margin-bottom: 8px;
    }

    .game-title span {
      color: var(--accent-blue);
    }

    .game-subtitle {
      font-size: 0.9rem;
      color: var(--text-muted);
      margin-bottom: 36px;
    }

    .name-label {
      display: block;
      text-align: left;
      font-size: 0.82rem;
      color: var(--text-muted);
      text-transform: uppercase;
      letter-spacing: 0.8px;
      margin-bottom: 8px;
    }

    .name-input {
      width: 100%;
      background: var(--bg-surface);
      border: 1px solid var(--border);
      border-radius: 8px;
      padding: 14px 18px;
      color: var(--text-primary);
      font-family: 'Fira Code', monospace;
      font-size: 1rem;
      margin-bottom: 22px;
      transition: border-color 0.2s;
    }

    .name-input:focus {
      outline: none;
      border-color: var(--accent-blue);
      box-shadow: 0 0 0 3px rgba(88, 166, 255, 0.1);
    }

    .btn-enter {
      width: 100%;
      background: var(--accent-blue);
      color: #fff;
      font-size: 1rem;
      font-weight: 700;
      padding: 15px;
      border-radius: 8px;
      border: none;
      letter-spacing: 1px;
      text-transform: uppercase;
      transition: all 0.3s ease;
      box-shadow: 0 0 24px rgba(88, 166, 255, 0.25);
    }

    .btn-enter:hover {
      background: #79b8ff;
      box-shadow: 0 0 36px rgba(88, 166, 255, 0.45);
      transform: translateY(-2px);
    }

    .game-stats {
      display: flex;
      justify-content: center;
      gap: 28px;
      margin-top: 30px;
      padding-top: 24px;
      border-top: 1px solid var(--border);
    }

    .stat-item {
      text-align: center;
    }

    .stat-value {
      font-family: 'Fira Code', monospace;
      font-size: 1.3rem;
      font-weight: 700;
      color: var(--accent-gold);
    }

    .stat-label {
      font-size: 0.75rem;
      color: var(--text-muted);
      text-transform: uppercase;
      letter-spacing: 0.5px;
    }
  </style>
</head>
<body>

  <!-- ── CODE RAIN BACKGROUND ── -->
  <div class="code-rain-container" id="rainContainer"></div>

  <div class="welcome-wrapper">
    <div class="welcome-card">

      <div class="game-logo">☕ JavaQuest</div>
      <h1 class="game-title">Concept <span>Conquest</span></h1>
      <p class="game-subtitle">18 rooms · 90 questions · Prove your Java mastery</p>

      <form action="start" method="post">
        <label class="name-label" for="playerName">Your Developer Name</label>
        <input
          class="name-input"
          type="text"
          id="playerName"
          name="playerName"
          placeholder="e.g. CodeNinja"
          maxlength="30"
          autocomplete="off"
          autofocus
        />
        <button class="btn-enter" type="submit">⚔ Enter the Dungeon</button>
      </form>

      <div class="game-stats">
        <div class="stat-item">
          <div class="stat-value">18</div>
          <div class="stat-label">Rooms</div>
        </div>
        <div class="stat-item">
          <div class="stat-value">90</div>
          <div class="stat-label">Questions</div>
        </div>
        <div class="stat-item">
          <div class="stat-value">1530</div>
          <div class="stat-label">Max Score</div>
        </div>
      </div>

    </div>
  </div>

  <script>
    // ── CODE RAIN GENERATOR ──
    const chars = 'int void class return if else for while new import static final public private boolean String[]{}();=<>';
    const container = document.getElementById('rainContainer');
    const columns = Math.floor(window.innerWidth / 22);

    for (let i = 0; i < columns; i++) {
      const span = document.createElement('span');
      span.classList.add('rain-char');
      span.textContent = chars[Math.floor(Math.random() * chars.length)];
      span.style.left = (i * 22) + 'px';
      span.style.animationDuration = (Math.random() * 2 + 1) + 's';
      span.style.animationDelay    = (Math.random() * 4) + 's';
      span.style.fontSize           = (Math.random() * 6 + 10) + 'px';
      span.style.opacity            = (Math.random() * 0.12 + 0.05).toString();
      container.appendChild(span);
    }
  </script>

</body>
</html>