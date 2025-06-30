<!-- <template>
  <div class="admin-layout">
    <router-view />
  </div>
</template>

<script setup>
</script>

<style scoped>
.admin-layout {
  padding: 20px;
}
</style> -->
<template>

  <div class="wrapper">
    <Sidebar />

    <main id="content">
      <router-view />
    </main>
  </div>

</template>

<script setup>
import Sidebar from '../components/Admin/Sidebar.vue';
</script>