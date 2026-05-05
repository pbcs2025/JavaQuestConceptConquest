// ── game.js — JavaQuest: Concept Conquest ──
// contextPath is injected by room.jsp as a <script> variable

// ── SUBMIT ANSWER (MCQ click or SUBMIT CODE button) ──
function submitAnswer(questionId, playerAnswer) {
  // Disable all buttons to prevent double submission
  disableAllOptions();

  fetch(contextPath + '/answer', {
    method: 'POST',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    body: 'questionId=' + encodeURIComponent(questionId)
        + '&answer='    + encodeURIComponent(playerAnswer)
  })
  .then(r => r.json())
  .then(data => {
    // Flash correct/wrong on the clicked element
    const el = document.getElementById('q-' + questionId);
    if (el) {
      el.classList.add(data.correct ? 'correct-flash' : 'wrong-flash');
    }

    // Update score display with pop animation
    const scoreEl = document.getElementById('score-display');
    if (scoreEl) {
      scoreEl.textContent = 'Score: ' + data.score;
      scoreEl.classList.remove('score-pop');
      void scoreEl.offsetWidth; // force reflow
      scoreEl.classList.add('score-pop');
      setTimeout(() => scoreEl.classList.remove('score-pop'), 400);
    }

    if (data.correct) {
      // Fill the next empty progress slot
      fillNextProgressSlot();
      // Check if all 5 answered → unlock ADVANCE button
      checkAdvanceUnlock();
      // Auto-load next question after 1.2s
      setTimeout(() => loadNextQuestion(), 1200);
    } else {
      // Re-enable buttons after wrong answer so player can retry
      setTimeout(() => enableAllOptions(), 800);
    }
  })
  .catch(err => console.error('Answer error:', err));
}

// ── REQUEST HINT ──
function requestHint(questionId) {
  fetch(contextPath + '/hint', {
    method: 'POST',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    body: 'questionId=' + encodeURIComponent(questionId)
  })
  .then(r => r.json())
  .then(data => {
    const hintText = document.getElementById('hint-text');
    const hintBox  = document.getElementById('hint-box');
    if (hintText) hintText.textContent = data.explanation;
    if (hintBox)  hintBox.style.display = 'block';

    // Update score
    const scoreEl = document.getElementById('score-display');
    if (scoreEl) scoreEl.textContent = 'Score: ' + data.score;
  })
  .catch(err => console.error('Hint error:', err));
}

// ── ADVANCE TO NEXT ROOM ──
function advanceRoom() {
  const padlock = document.getElementById('padlock');
  if (padlock) {
    padlock.classList.add('unlock-pop');
  }
  setTimeout(() => {
    fetch(contextPath + '/advance', { method: 'POST' })
    .then(() => {
      window.location.href = contextPath + '/room';
    })
    .catch(err => console.error('Advance error:', err));
  }, 800);
}

// ── FILL NEXT PROGRESS SLOT ──
function fillNextProgressSlot() {
  const slots = document.querySelectorAll('.progress-slot:not(.filled)');
  if (slots.length > 0) {
    slots[0].classList.add('filled');
  }
}

// ── CHECK IF ALL 5 ANSWERED → UNLOCK ADVANCE ──
function checkAdvanceUnlock() {
  const remaining = document.querySelectorAll('.progress-slot:not(.filled)');
  if (remaining.length === 0) {
    const btn     = document.getElementById('advance-btn');
    const padlock = document.getElementById('padlock');
    if (btn) {
      btn.disabled = false;
      btn.classList.add('fade-in');
    }
    if (padlock) {
      padlock.textContent = '🔓';
      padlock.classList.add('unlock-pop');
    }
  }
}

// ── LOAD NEXT UNANSWERED QUESTION ──
function loadNextQuestion() {
  // Reload the page — RoomServlet will serve next unanswered question
  window.location.reload();
}

// ── DISABLE ALL OPTION BUTTONS ──
function disableAllOptions() {
  document.querySelectorAll('.opt-btn').forEach(btn => {
    btn.disabled = true;
  });
  const codeInput  = document.getElementById('code-answer-input');
  const submitCode = document.getElementById('submit-code-btn');
  if (codeInput)  codeInput.disabled  = true;
  if (submitCode) submitCode.disabled = true;
}

// ── RE-ENABLE ALL OPTION BUTTONS ──
function enableAllOptions() {
  document.querySelectorAll('.opt-btn').forEach(btn => {
    btn.disabled = false;
  });
  const codeInput  = document.getElementById('code-answer-input');
  const submitCode = document.getElementById('submit-code-btn');
  if (codeInput)  codeInput.disabled  = false;
  if (submitCode) submitCode.disabled = false;
}

// ── ENTER KEY ON CODE INPUT ──
document.addEventListener('DOMContentLoaded', () => {
  const codeInput = document.getElementById('code-answer-input');
  if (codeInput) {
    codeInput.addEventListener('keydown', (e) => {
      if (e.key === 'Enter') {
        const submitBtn = document.getElementById('submit-code-btn');
        if (submitBtn) submitBtn.click();
      }
    });
  }
});

// ── SCORE COUNTER ANIMATION (Game Over screen) ──
function animateScore(targetScore) {
  const el = document.getElementById('final-score');
  if (!el) return;
  let current = 0;
  const step = Math.ceil(targetScore / 60);
  const timer = setInterval(() => {
    current += step;
    if (current >= targetScore) {
      current = targetScore;
      clearInterval(timer);
    }
    el.textContent = current;
  }, 20);
}