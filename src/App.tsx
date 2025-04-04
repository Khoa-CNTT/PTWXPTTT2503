import { Navigate, Route, Routes } from "react-router-dom";
import { Toaster } from "react-hot-toast";
import { useAuthStore } from "./store/authStore";
import { AuthLayout } from "./layouts/AuthLayout";
import { LoginPage } from "./Login/loginpage";

const App = () => {
  const { isAuthenticated } = useAuthStore();

  return (
    <>
      <Routes>
        
        <Route
          path="/login"
          element={
            isAuthenticated ? (
              <Navigate to={"/"} />
            ) : (
              <AuthLayout>
                <LoginPage />
              </AuthLayout>
            )
          }
        />
        
      </Routes>

      <Toaster />
    </>
  );
};

export default App;
