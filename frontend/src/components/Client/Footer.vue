<template>
  <footer class="client-footer">
    <div class="footer-container">
      <p>Email: support@nextvibe.vn | Hotline: 0123 456 789</p>
      <p>Địa chỉ: 123 Đường clothing, TP. Hà Nội</p>
      <p>© 2025 Nextvibe. All rights reserved.</p>
    </div>
  </footer>
</template>

<script setup>
</script>

<style scoped>
.client-footer {
  background-color: #333;
  color: white;
  padding: 20px;
  text-align: center;
  width: 100%;
  box-sizing: border-box;
}

.footer-container p {
  font-size: 16px;
  margin: 10px 0;
}

@media screen and (max-width: 768px) {
  .footer-container p {
    font-size: 14px;
  }
}
</style>