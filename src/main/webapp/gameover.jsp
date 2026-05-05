<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Game Over | JavaQuest</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animations.css"/>
  <style>
    /* ── GAME OVER LAYOUT ── */
    .gameover-wrapper {
      min-height: 100vh;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      padding: 40px 20px;
      gap: 28px;
    }

    /* ── TOP TITLE ── */
    .gameover-title {
      font-family: 'Fira Code', monospace;
      font-size: 1rem;
      color: var(--text-muted);
      text-transform: uppercase;
      letter-spacing: 2px;
    }

    /* ── RANK BADGE CARD ── */
    .rank-card {
      background: var(--bg-panel);
      border: 1px solid var(--border);
      border-radius: 16px;
      padding: 40px 52px;
      text-align: center;
      max-width: 460px;
      width: 100%;
      animation: welcomeSlideUp 0.5s ease-out forwards;
    }

    .rank-emoji {
      font-size: 4rem;
      margin-bottom: 14px;
      display: block;
      animation: badgePop 0.7s cubic-bezier(0.34,1.56,0.64,1) 0.3s both;
    }

    .rank-label {
      font-size: 0.8rem;
      color: var(--text-muted);
      text-transform: uppercase;
      letter-spacing: 1px;
      margin-bottom: 6px;
    }

    .rank-title {
      font-size: 2rem;
      font-weight: 700;
      color: var(--accent-gold);
      margin-bottom: 10px;
      animation: badgePop 0.7s cubic-bezier(0.34,1.56,0.64,1) 0.5s both;
    }

    .rank-message {
      font-size: 0.92rem;
      color: var(--text-muted);
      line-height: 1.6;
      margin-bottom: 28px;
    }

    /* ── FINAL SCORE ── */
    .final-score-row {
      display: flex;
      align-items: baseline;
      justify-content: center;
      gap: 8px;
      margin-bottom: 6px;
    }

    .final-score-number {
      font-family: 'Fira Code', monospace;
      font-size: 3.2rem;
      font-weight: 700;
      color: var(--accent-gold);
      animation: countUp 0.6s ease-out 0.4s both;
    }

    .final-score-label {
      font-size: 1rem;
      color: var(--text-muted);
    }

    /* ── STATS ROW ── */
    .stats-row {
      display: flex;
      justify-content: center;
      gap: 28px;
      margin-top: 20px;
      padding-top: 20px;
      border-top: 1px solid var(--border);
    }

    .stat-box {
      text-align: center;
    }

    .stat-box .stat-value {
      font-family: 'Fira Code', monospace;
      font-size: 1.5rem;
      font-weight: 700;
      color: var(--text-primary);
    }

    .stat-box .stat-label {
      font-size: 0.75rem;
      color: var(--text-muted);
      text-transform: uppercase;
      letter-spacing: 0.5px;
      margin-top: 2px;
    }

    /* ── BREAKDOWN TABLE ── */
    .breakdown-card {
      background: var(--bg-panel);
      border: 1px solid var(--border);
      border-radius: 12px;
      padding: 28px;
      max-width: 700px;
      width: 100%;
      animation: fadeIn 0.6s ease-out 0.4s both;
    }

    .breakdown-card h3 {
      font-size: 1rem;
      color: var(--text-muted);
      text-transform: uppercase;
      letter-spacing: 1px;
      margin-bottom: 18px;
      font-family: 'Fira Code', monospace;
    }

    .breakdown-table {
      width: 100%;
      border-collapse: collapse;
      font-size: 0.88rem;
    }

    .breakdown-table th {
      text-align: left;
      color: var(--text-muted);
      font-weight: 600;
      padding: 8px 12px;
      border-bottom: 1px solid var(--border);
      text-transform: uppercase;
      letter-spacing: 0.5px;
      font-size: 0.78rem;
    }

    .breakdown-table td {
      padding: 10px 12px;
      border-bottom: 1px solid rgba(48,54,61,0.5);
      color: var(--text-primary);
    }

    .breakdown-table tr:last-child td {
      border-bottom: none;
    }

    .breakdown-table tr:hover td {
      background: rgba(88,166,255,0.04);
    }

    .room-num-cell {
      font-family: 'Fira Code', monospace;
      color: var(--accent-gold);
      font-size: 0.82rem;
    }

    .score-cell {
      font-family: 'Fira Code', monospace;
      color: var(--correct-green);
      font-weight: 600;
    }

    /* ── PLAY AGAIN BUTTON ── */
    .btn-play-again {
      background: var(--accent-blue);
      color: #fff;
      font-size: 1rem;
      font-weight: 700;
      padding: 15px 40px;
      border-radius: 8px;
      border: none;
      letter-spacing: 1px;
      text-transform: uppercase;
      transition: all 0.3s ease;
      box-shadow: 0 0 24px rgba(88,166,255,0.25);
      animation: fadeIn 0.6s ease-out 0.6s both;
    }

    .btn-play-again:hover {
      background: #79b8ff;
      box-shadow: 0 0 36px rgba(88,166,255,0.45);
      transform: translateY(-2px);
    }
  </style>
</head>
<body>

  <div class="gameover-wrapper">

    <div class="gameover-title">☕ JavaQuest: Concept Conquest — Complete</div>

    <!-- ── RANK BADGE CARD ── -->
    <div class="rank-card">

      <!-- Rank Emoji -->
      <c:choose>
        <c:when test="${rank == 'Legend'}">
          <span class="rank-emoji">🏆</span>
        </c:when>
        <c:when test="${rank == 'Architect'}">
          <span class="rank-emoji">🥇</span>
        </c:when>
        <c:when test="${rank == 'Developer'}">
          <span class="rank-emoji">⚔️</span>
        </c:when>
        <c:otherwise>
          <span class="rank-emoji">🌱</span>
        </c:otherwise>
      </c:choose>

      <div class="rank-label">Your Rank</div>
      <div class="rank-title">${rank}</div>

      <!-- Rank Message -->
      <c:choose>
        <c:when test="${rank == 'Legend'}">
          <p class="rank-message">Master of Java. The dungeon is conquered!</p>
        </c:when>
        <c:when test="${rank == 'Architect'}">
          <p class="rank-message">Solid command of Java. The dungeon bows.</p>
        </c:when>
        <c:when test="${rank == 'Developer'}">
          <p class="rank-message">You know your Java. The dungeon respects you.</p>
        </c:when>
        <c:otherwise>
          <p class="rank-message">You have entered the dungeon. Keep learning!</p>
        </c:otherwise>
      </c:choose>

      <!-- Final Score -->
      <div class="final-score-row">
        <span class="final-score-number" id="final-score">0</span>
        <span class="final-score-label">pts</span>
      </div>

      <!-- Stats Row -->
      <div class="stats-row">
        <div class="stat-box">
          <div class="stat-value">${sessionScope.roomsCompleted}</div>
          <div class="stat-label">Rooms Done</div>
        </div>
        <div class="stat-box">
          <div class="stat-value">${sessionScope.hintsUsed}</div>
          <div class="stat-label">Hints Used</div>
        </div>
        <div class="stat-box">
          <div class="stat-value">${sessionScope.wrongAttempts}</div>
          <div class="stat-label">Wrong Attempts</div>
        </div>
      </div>

    </div>

    <!-- ── BREAKDOWN TABLE ── -->
    <div class="breakdown-card">
      <h3>// Room Breakdown</h3>
      <table class="breakdown-table">
        <thead>
          <tr>
            <th>#</th>
            <th>Concept</th>
            <th>Difficulty</th>
            <th>Status</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="room" items="${allRooms}" varStatus="s">
            <tr>
              <td class="room-num-cell">R${room.roomId}</td>
              <td>${room.conceptName}</td>
              <td>
                <c:choose>
                  <c:when test="${room.difficulty == 'Easy'}">
                    <span class="pill pill-easy">${room.difficulty}</span>
                  </c:when>
                  <c:when test="${room.difficulty == 'Medium'}">
                    <span class="pill pill-medium">${room.difficulty}</span>
                  </c:when>
                  <c:when test="${room.difficulty == 'Hard'}">
                    <span class="pill pill-hard">${room.difficulty}</span>
                  </c:when>
                  <c:otherwise>
                    <span class="pill pill-expert">${room.difficulty}</span>
                  </c:otherwise>
                </c:choose>
              </td>
              <td class="score-cell">
                <c:choose>
                  <c:when test="${s.index < sessionScope.roomsCompleted}">✅ Cleared</c:when>
                  <c:otherwise><span style="color:var(--text-muted)">— Locked</span></c:otherwise>
                </c:choose>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>

    <!-- ── PLAY AGAIN ── -->
    <button class="btn-play-again"
            onclick="window.location.href='${pageContext.request.contextPath}/'">
      ⚔ Play Again
    </button>

  </div>

  <script src="${pageContext.request.contextPath}/js/game.js"></script>
  <script>
    // Animate score count-up on page load
    animateScore(${sessionScope.score});
  </script>

</body>
</html>