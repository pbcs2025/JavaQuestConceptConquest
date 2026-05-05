<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Room ${room.roomId} — ${room.conceptName} | JavaQuest</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/room.css"/>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/questions.css"/>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animations.css"/>
</head>
<body>

  <!-- ── HEADER BAR ── -->
  <div class="header-bar">
    <div class="logo">☕ JavaQuest: Concept Conquest</div>
    <div class="player-info">
      <span>👾 ${sessionScope.playerName}</span>
      <span id="score-display">Score: ${sessionScope.score}</span>
    </div>
  </div>

  <!-- ── ERROR BOX (if advance attempted too early) ── -->
  <c:if test="${not empty sessionScope.advanceError}">
    <div class="error-box" style="margin: 12px 28px 0;">
      ⚠ ${sessionScope.advanceError}
    </div>
    <% session.removeAttribute("advanceError"); %>
  </c:if>

  <!-- ── MAIN GAME LAYOUT ── -->
  <div class="game-layout">

    <!-- ════ LEFT — CONCEPT SIDEBAR ════ -->
    <div class="concept-sidebar">

      <div class="room-badge">Room ${room.roomId} / 18</div>

      <h2 class="concept-title">${room.conceptName}</h2>

      <!-- Difficulty Pill -->
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

      <p class="concept-summary">${room.conceptSummary}</p>

      <!-- Progress Bar — 5 slots -->
      <div class="progress-section">
        <div class="progress-label">Progress — ${answeredCount} / 5 answered</div>
        <div class="progress-bar">
          <c:forEach begin="1" end="5" varStatus="s">
            <div class="progress-slot ${s.index <= answeredCount ? 'filled' : ''}"></div>
          </c:forEach>
        </div>
      </div>

      <!-- ADVANCE Button -->
      <div class="advance-section">
        <button
          class="btn-advance"
          id="advance-btn"
          onclick="advanceRoom()"
          ${answeredCount < 5 ? 'disabled' : ''}
        >
          <span class="padlock-icon" id="padlock">
            ${answeredCount >= 5 ? '🔓' : '🔒'}
          </span>
          <c:choose>
            <c:when test="${room.roomId == 18}">Complete Quest</c:when>
            <c:otherwise>Advance to Next Room</c:otherwise>
          </c:choose>
        </button>
      </div>

    </div>
    <!-- ════ END LEFT SIDEBAR ════ -->


    <!-- ════ RIGHT — QUESTION PANEL ════ -->
    <div class="question-panel">

      <c:choose>

        <!-- ── ALL 5 ANSWERED ── -->
        <c:when test="${answeredCount >= 5}">
          <div class="card fade-in" style="text-align:center; padding: 48px 28px;">
            <div style="font-size: 3rem; margin-bottom: 16px;">🎉</div>
            <h3 style="color: var(--correct-green); font-size: 1.4rem; margin-bottom: 10px;">
              Room ${room.roomId} Mastered!
            </h3>
            <p style="color: var(--text-muted); margin-bottom: 24px;">
              All 5 questions answered correctly. Click <strong>Advance</strong> to continue.
            </p>
            <div style="font-family:'Fira Code',monospace; color:var(--accent-gold); font-size:0.9rem;">
              +15 bonus points await →
            </div>
          </div>
        </c:when>

        <!-- ── SHOW CURRENT QUESTION ── -->
        <c:otherwise>
          <div class="question-card card-flip-in" id="question-card">

            <!-- Question Meta Row -->
            <div class="question-meta">
              <span class="question-counter">
                Question ${answeredCount + 1} of 5
              </span>
              <c:choose>
                <c:when test="${currentQuestion.type == 'MCQ'}">
                  <span class="type-badge-mcq">MCQ</span>
                </c:when>
                <c:otherwise>
                  <span class="type-badge-code">CODE</span>
                </c:otherwise>
              </c:choose>
            </div>

            <!-- Question Text -->
            <p class="question-text">${currentQuestion.text}</p>

            <!-- ── MCQ OPTIONS ── -->
            <c:if test="${currentQuestion.type == 'MCQ'}">
              <div class="options-grid">
                <c:forEach var="opt" items="${currentQuestion.options}" varStatus="s">
                  <button
                    class="opt-btn"
                    id="q-${currentQuestion.id}"
                    onclick="submitAnswer('${currentQuestion.id}', '${opt}')"
                  >
                    <span class="opt-label">
                      <c:choose>
                        <c:when test="${s.index == 0}">A</c:when>
                        <c:when test="${s.index == 1}">B</c:when>
                        <c:when test="${s.index == 2}">C</c:when>
                        <c:otherwise>D</c:otherwise>
                      </c:choose>
                    </span>
                    ${opt}
                  </button>
                </c:forEach>
              </div>
            </c:if>

            <!-- ── CODE QUESTION ── -->
            <c:if test="${currentQuestion.type == 'CODE'}">
              <div class="code-block">
                <code>${currentQuestion.text}</code>
              </div>
              <div class="code-answer-area">
                <input
                  class="code-input"
                  type="text"
                  id="code-answer-input"
                  placeholder="Type your answer here..."
                  autocomplete="off"
                  spellcheck="false"
                />
                <button
                  class="btn-submit-code"
                  id="submit-code-btn"
                  onclick="submitAnswer('${currentQuestion.id}',
                           document.getElementById('code-answer-input').value)"
                >
                  Submit Code
                </button>
              </div>
            </c:if>

            <!-- Hint Button & Box -->
            <button class="btn-hint"
                    onclick="requestHint('${currentQuestion.id}')">
              💡 Need a hint? (−5 pts)
            </button>
            <div class="hint-box" id="hint-box">
              <p id="hint-text"></p>
            </div>

          </div>
        </c:otherwise>
      </c:choose>

    </div>
    <!-- ════ END RIGHT PANEL ════ -->

  </div>

  <!-- Inject context path for game.js fetch calls -->
  <script>
    const contextPath = '${pageContext.request.contextPath}';
  </script>
  <script src="${pageContext.request.contextPath}/js/game.js"></script>

</body>
</html>