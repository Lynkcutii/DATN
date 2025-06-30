<template>
  <div>
    <Header />
    <main class="container my-4" style="min-height: 70vh;">
      <router-view />
    </main>
    <Footer />
  </div>
</template>
<script setup>
import Header from '../user/Header.vue';
import Footer from '../user/Footer.vue';
</script>